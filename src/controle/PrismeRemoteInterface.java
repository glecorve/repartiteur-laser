/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modele.Message;

/**
 *
 * @author Gwenole Lecorve
 */
public interface PrismeRemoteInterface extends Remote {
    
    public void orienter(String depuisUrl, String versUrl) throws RemoteException;
    
    public void transmettre(Message message) throws RemoteException;
    
    public void enregisterConnecteur(String url) throws RemoteException;
    
    public void oublierConnecteur(String url) throws RemoteException;
    
}
