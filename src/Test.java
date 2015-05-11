
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gwenole Lecorve
 */
public class Test extends UnicastRemoteObject implements Remote {
    
    public Test() throws RemoteException {
        
    }
}
