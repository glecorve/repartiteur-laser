/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modele.ConnecteurException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import modele.Annuaire;
import modele.PrismeException;

/**
 *
 * @author Gwenole Lecorve
 */
public interface ConnecteurLocalInterface {
    
    public String getUrl();
        
    public boolean estConnecte();
    
    public Annuaire getAnnuaire();
    
    public void connecterFibreOptique() throws ConnecteurException, RemoteException, MalformedURLException, NotBoundException;
    
    public void deconnecterFibreOptique() throws ConnecteurException, RemoteException, MalformedURLException, NotBoundException;
    
    public void emettreMessage(String url, String message) throws InterruptedException, RemoteException, ConnecteurException, PrismeException;
    
    public void enregistrerConnecteur(String url, ConnecteurRemoteInterface distant);
    
    public void recevoirAutorisation();
    
}
