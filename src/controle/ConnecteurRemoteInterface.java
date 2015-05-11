/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modele.ConnecteurException;

/**
 *
 * @author Gwenole Lecorve
 */
public interface ConnecteurRemoteInterface extends Remote {

    public String getRemoteUrl() throws RemoteException;

    public void enregistrerConnecteur(String urlConnecteur, String urlControleur) throws RemoteException;

//    public void enregistrerConnecteur(String urlConnecteur, ConnecteurRemoteInterface connecteurDistant) throws RemoteException;
    
    public void oublierConnecteur(String urlConnecteur) throws RemoteException;

    public void enregistrerControleur(String urlControleur) throws RemoteException;

    public void oublierControleur(String urlControleur) throws RemoteException;

    public void recevoirMessage(modele.Message transmission) throws RemoteException, ConnecteurException;

}
