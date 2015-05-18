/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import modele.Annuaire;
import modele.ConnecteurException;
import modele.Message;
import modele.Prisme;
import modele.PrismeException;

/**
 *
 * @author Gwenole Lecorve
 */
public class PrismeBackend extends UnicastRemoteObject implements PrismeRemoteInterface {

    protected String url;
    protected Prisme prisme;
    protected Annuaire connecteurs;

    public PrismeBackend(String _url) throws RemoteException, MalformedURLException {
        this.url = _url;
        prisme = new Prisme();
        connecteurs = new Annuaire();
        Naming.rebind(url, (PrismeRemoteInterface) this);
    }

    @Override
    public void finalize() throws RemoteException, NotBoundException, MalformedURLException, Throwable {
        try {
            Naming.unbind(url);
        } finally {
            super.finalize();
        }
    }

    public Prisme getPrisme() {
        return prisme;
    }

    public Annuaire getAnnuaire() {
        return connecteurs;
    }

    @Override
    public synchronized void orienter(String depuisUrl, String versUrl) throws RemoteException, PrismeException {
        System.out.println(url + ": \tOrientation de " + depuisUrl + " vers " + versUrl);
        prisme.orienter(depuisUrl, versUrl);
    }

    @Override
    public synchronized void transmettre(Message message) throws RemoteException, ConnecteurException, PrismeException {
        try {
            prisme.demarrerTransmission();
            System.out.println(url + ": \tTranmission du message \"" + message.toString() + "\"");
            ConnecteurRemoteInterface c = connecteurs.chercherUrl(prisme.getVersUrl());
            c.recevoirMessage(message);
        } catch (RemoteException ex) {
            throw ex;
        } catch (ConnecteurException ex) {
            throw ex;
        } finally {
            prisme.stopperTransmission();
        }
    }

    @Override
    public synchronized void enregisterConnecteur(String urlConnecteurDistant) throws RemoteException, NotBoundException, MalformedURLException {
        System.out.println(url + ": \tEnregistrement du connecteur " + urlConnecteurDistant);
        ConnecteurRemoteInterface connecteurDistant = (ConnecteurRemoteInterface) Naming.lookup(urlConnecteurDistant);
        connecteurs.ajouterConnecteurDistant(urlConnecteurDistant, connecteurDistant);
    }

    @Override
    public synchronized void oublierConnecteur(String urlConnecteurDistant) throws RemoteException {
        System.out.println(url + ": \tOubli du connecteur " + urlConnecteurDistant);
        connecteurs.retirerConnecteurDistant(urlConnecteurDistant);
    }

}
