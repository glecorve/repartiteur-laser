/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import modele.ConnecteurException;
import modele.Message;
import modele.PrismeException;

/**
 *
 * @author Gwenole Lecorve
 */
public interface PrismeRemoteInterface extends Remote {
    
    public void orienter(String depuisUrl, String versUrl) throws RemoteException, PrismeException;
    
    public void transmettre(Message message) throws RemoteException, ConnecteurException, PrismeException;
    
    public void enregisterConnecteur(String url) throws RemoteException, NotBoundException, MalformedURLException;
    
    public void oublierConnecteur(String url) throws RemoteException;
    
}
