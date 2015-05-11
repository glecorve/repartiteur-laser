/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modele.ConnecteurException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Annuaire;
import modele.Connecteur;
import modele.Message;

/**
 *
 * @author Gwenole Lecorve
 */
public class ConnecteurBackend extends UnicastRemoteObject implements ConnecteurLocalInterface, ConnecteurRemoteInterface {
    
    protected String url;
    protected String controleurUrl;

    protected Connecteur connecteur;
    final protected ControleurInterface controleur;
    
    protected Annuaire connecteursDistants;

    protected Semaphore semaphore;
    
    public ConnecteurBackend(String url, Connecteur connecteur) throws RemoteException, MalformedURLException {
        
        this.url = url;
        controleurUrl = url+"/controleur";

        this.connecteur = connecteur;
        this.controleur = new StupideControleur(controleurUrl, this);

        this.connecteursDistants = new Annuaire();
        
        this.semaphore = new Semaphore(0, true);
    }
    
    @Override
    public void finalize() throws ConnecteurException, RemoteException, NotBoundException, MalformedURLException {
        deconnecterFibreOptique();
        Naming.unbind(url);
    }

    @Override
    public String getUrl() {
        return url;
    }
    
    @Override
    public String getRemoteUrl() {
        return url;
    }

    @Override
    public boolean estConnecte() {
        return connecteur.estConnecte();
    }

    @Override
    public Annuaire getAnnuaire() {
        return connecteursDistants;
    }

    @Override
    public void connecterFibreOptique() throws ConnecteurException, RemoteException, MalformedURLException, NotBoundException {
        // Enregistrer dans l'annuaire RMI
        Naming.rebind(url, (ConnecteurRemoteInterface) this);
        
        // Enregistrement de tous les autres connecteurs
        // et notification a tous les autres connecteurs
        for (String name : Naming.list("rmi://localhost:1099/")) {
            name = "rmi:" + name;
            if (!name.equals(url)) {
                Remote o = Naming.lookup(name);
                if (o instanceof ConnecteurRemoteInterface) {
                    // Enregistrement du connecteur courant
                    System.out.println(url + ": Enregistrement auprès de " + name + " (" + ((ConnecteurRemoteInterface) o).getRemoteUrl() + ")");
                    ((ConnecteurRemoteInterface) o).enregistrerConnecteur(url, controleurUrl);
                    // Enregistrement d'un connecteur distant
                    enregistrerConnecteur(name, (ConnecteurRemoteInterface) o);
                }
            }
        }
        connecteur.connecter();
    }

    @Override
    public void deconnecterFibreOptique() throws ConnecteurException, RemoteException, MalformedURLException, NotBoundException {
        // Enregistrer dans l'annuaire RMI
        Naming.unbind(url);
        
        for (ConnecteurRemoteInterface distant : connecteursDistants.getConnecteursDistants().values()) {
            try {
                distant.oublierConnecteur(url);
                distant.oublierControleur(controleurUrl);
            } catch (RemoteException ex) {
                Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        connecteursDistants.vider();
        connecteur.deconnecter();
    }

    @Override
    public void recevoirMessage(modele.Message message) throws RemoteException, ConnecteurException {
        if (!message.getUrlDestinataire().equals(url)) {
            throw new ConnecteurException("Message recu par le mauvais destinataire (" + message.getUrlDestinataire() +  " != " + url + ")");
        }
        System.out.println(url + ": Message recu de " + message.getUrlEmetteur() + " \"" + message.getContenu() + "\"");
        connecteur.ajouterMessage(message);
    }

    @Override
    public synchronized void emettreMessage(String urlDistant, String message) throws InterruptedException, RemoteException, ConnecteurException {
        synchronized (controleur) {
            controleur.demanderSectionCritique();
            semaphore.acquire();
            System.out.println("Émission vers " + urlDistant + ": " + message);
            ConnecteurRemoteInterface distant = connecteursDistants.chercherUrl(urlDistant);
            distant.recevoirMessage(new Message(url, urlDistant, message));
            controleur.quitterSectionCritique();
        }
    }

    @Override
    public void enregistrerConnecteur(String urlDistant, ConnecteurRemoteInterface distant) {
        connecteursDistants.ajouterConnecteurDistant(urlDistant, distant);
        try {
            System.out.println(url + ": Enregistrement de " + distant.getRemoteUrl());
        } catch (RemoteException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void enregistrerConnecteur(String urlDistante, String urlControleurDistant) {
        try {
            ConnecteurRemoteInterface o = (ConnecteurRemoteInterface) Naming.lookup(urlDistante);
            enregistrerConnecteur(urlDistante, o);
            enregistrerControleur(urlControleurDistant);
        } catch (NotBoundException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void oublierConnecteur(String urlDistant) {
        System.out.println(url + ": Oubli de " + urlDistant);
        connecteursDistants.retirerConnecteurDistant(urlDistant);
    }

    @Override
    public void enregistrerControleur(String urlDistante) {
        controleur.enregistrerControleur(urlDistante);
    }

    @Override
    public void oublierControleur(String urlDistante) {
        controleur.oublierControleur(urlDistante);
    }

    @Override
    public synchronized void recevoirAutorisation() {
        semaphore.release();
    }
    
}
