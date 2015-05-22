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

    public void enregistrerConnecteur(String urlConnecteurDistant, String urlControleurDistant) throws RemoteException;
    
    public void oublierConnecteur(String urlConnecteurDistant, String urlControleurDistant) throws RemoteException;

    public void enregistrerControleur(String urlControleurDistant) throws RemoteException;

    public void oublierControleur(String urlControleurDistant) throws RemoteException;

    public void recevoirMessage(modele.Message transmission) throws RemoteException, ConnecteurException;

}
