/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controle.ConnecteurRemoteInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 *
 * @author Gwenole Lecorve
 */
public class Annuaire extends Observable {
    
    protected Map<String, ConnecteurRemoteInterface> connecteursDistants;
    
    public Annuaire() {
        connecteursDistants = new HashMap();
    }
    
    public Map<String, ConnecteurRemoteInterface>getConnecteursDistants() {
        return connecteursDistants;
    }
    
    public void ajouterConnecteurDistant(String url, ConnecteurRemoteInterface connecteur) {
        connecteursDistants.put(url, connecteur);
        notifierObservateurs();
    }
    
    public void retirerConnecteurDistant(String url) {
        connecteursDistants.remove(url);
        notifierObservateurs();
    }

    public void vider() {
        connecteursDistants.clear();
        notifierObservateurs();
    }

    protected void notifierObservateurs() {
        super.setChanged();
        notifyObservers();
    }

    public ConnecteurRemoteInterface chercherUrl(String urlDistant) throws ConnecteurException {
        ConnecteurRemoteInterface connecteur = connecteursDistants.get(urlDistant);
        if (connecteur == null) { throw new ConnecteurException("Connecteur " + urlDistant + " introuvable dans l'annuaire local."); }
        else { return connecteur; }
    }
    
}
