/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.rmi.RemoteException;
import modele.Annuaire;
import modele.Message;
import modele.Prisme;

/**
 *
 * @author Gwenole Lecorve
 */
public class PrismeBackend implements PrismeRemoteInterface {
    
    protected Prisme prisme;
    protected Annuaire connecteurs;

    @Override
    public void orienter(String depuisUrl, String versUrl) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transmettre(Message message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enregisterConnecteur(String url) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void oublierConnecteur(String url) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
