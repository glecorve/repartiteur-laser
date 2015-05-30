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
import modele.PrismeException;

/**
 *
 * @author Gwenole Lecorve
 */
public class ConnecteurBackend extends UnicastRemoteObject implements ConnecteurLocalInterface, ConnecteurRemoteInterface {
    
    protected String url;
    protected String controleurUrl;
    protected String prismeUrl;

    protected Connecteur connecteur;
    final protected ControleurInterface controleur;
    protected PrismeRemoteInterface prisme;
    
    protected Annuaire connecteursDistants;

    protected Semaphore semaphore;
    
    public ConnecteurBackend(String _url, Connecteur _connecteur) throws RemoteException, MalformedURLException {
        
        this.url = _url;
        this.controleurUrl = _url+"/controleur";
        this.prismeUrl = "";
        
        this.connecteur = _connecteur;
        this.controleur = new StupideControleur(controleurUrl, this);
        this.prisme = null;
        
        this.connecteursDistants = new Annuaire();
        
        this.semaphore = new Semaphore(0, true);
    }
    
    @Override
    public void finalize() throws ConnecteurException, RemoteException, NotBoundException, MalformedURLException, Throwable {
        try {
            deconnecterFibreOptique();
            Naming.unbind(url);
        } finally {
            super.finalize();
        }
    }

    @Override
    public String getUrl() {
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
                    System.out.println(url + ": \tEnregistrement auprès de " + name);
                    ((ConnecteurRemoteInterface) o).enregistrerConnecteur(url, controleurUrl);
                    // Enregistrement d'un connecteur distant
                    enregistrerConnecteur(name, (ConnecteurRemoteInterface) o);
                }
                else if (o instanceof PrismeRemoteInterface) {
                    if (prisme == null) {
                        this.prismeUrl = name;
                        prisme = (PrismeRemoteInterface) o;
                        prisme.enregisterConnecteur(url);
                    }
                    else {
                        throw new ConnecteurException("Plusieurs prismes semblent exister.");
                    }
                }
            }
        }
        connecteur.connecter();
    }

    @Override
    public void deconnecterFibreOptique() throws ConnecteurException, RemoteException, MalformedURLException, NotBoundException {
        // Prisme
        prisme.oublierConnecteur(url);
        prismeUrl = "";
        prisme = null;
        
        // Autres connecteurs
        for (ConnecteurRemoteInterface distant : connecteursDistants.getConnecteursDistants().values()) {
            try {
                distant.oublierConnecteur(url, controleurUrl);
            } catch (RemoteException ex) {
                Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        connecteursDistants.vider();
        
        // Connecteur
        connecteur.deconnecter();
        
        // Annuaire RMI
        Naming.unbind(url);
    }

    @Override
    public synchronized void recevoirMessage(modele.Message message) throws RemoteException, ConnecteurException {
        if (!message.getUrlDestinataire().equals(url)) {
            throw new ConnecteurException("Message recu par le mauvais destinataire (" + message.getUrlDestinataire() +  " != " + url + ")");
        }
        System.out.println(url + ": \tMessage recu de " + message.getUrlEmetteur() + " \"" + message.getContenu() + "\"");
        connecteur.ajouterMessage(message);
    }

    @Override
    public void emettreMessage(String urlDistant, String message) throws InterruptedException, RemoteException, ConnecteurException, PrismeException {
            controleur.demanderSectionCritique();
            semaphore.acquire();
            System.out.println(url + ": \tEntree en section critique");
            System.out.println(url + ": \tÉmission vers " + urlDistant + ": " + message);
//            ConnecteurRemoteInterface distant = connecteursDistants.chercherUrl(urlDistant);
//            distant.recevoirMessage(new Message(url, urlDistant, message));
            prisme.orienter(url, urlDistant);
            prisme.transmettre(new Message(url, urlDistant, message));
            controleur.quitterSectionCritique();
            System.out.println(url + ": \tSortie de la section critique");
    }

    @Override
    public void enregistrerConnecteur(String urlDistant, ConnecteurRemoteInterface distant) {
        connecteursDistants.ajouterConnecteurDistant(urlDistant, distant);
        System.out.println(url + ": \tEnregistrement du connecteur " + urlDistant);
    }

    @Override
    public synchronized void enregistrerConnecteur(String urlConnecteurDistant, String urlControleurDistant) {
        try {
            ConnecteurRemoteInterface o = (ConnecteurRemoteInterface) Naming.lookup(urlConnecteurDistant);
            enregistrerConnecteur(urlConnecteurDistant, o);
            enregistrerControleur(urlControleurDistant);
            o.enregistrerControleur(controleurUrl);
        } catch (NotBoundException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void oublierConnecteur(String urlDistant) {
        System.out.println(url + ": \tOubli du connecteur " + urlDistant);
        connecteursDistants.retirerConnecteurDistant(urlDistant);
    }
    
    @Override
    public synchronized void oublierConnecteur(String urlConnecteurDistant, String urlControleurDistant) {
        try {
            ConnecteurRemoteInterface o = (ConnecteurRemoteInterface) Naming.lookup(urlConnecteurDistant);
            oublierConnecteur(urlConnecteurDistant);
            oublierControleur(urlControleurDistant);
            o.oublierControleur(controleurUrl);
        } catch (NotBoundException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ConnecteurBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void enregistrerControleur(String urlDistante) {
        controleur.enregistrerControleur(urlDistante);
    }

    @Override
    public synchronized void oublierControleur(String urlDistante) {
        controleur.oublierControleur(urlDistante);
    }

    @Override
    public void recevoirAutorisation() {
        semaphore.release();
    }
    
}
