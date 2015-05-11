/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Gwenole Lecorve
 */
public class Connecteur extends Observable {
    
    protected boolean connecte;
    protected List<Message> tampon;
    
    public Connecteur() {
        connecte = false;
        tampon = new LinkedList();
    }

    public boolean estConnecte() {
        return connecte;
    }
    
    public void connecter() {
        connecte = true;
    }
    
    public void deconnecter() {
        connecte = false;
    }
    
    public void ajouterMessage(Message message) {
        tampon.add(message);
        notifierObservateurs();
    }
    
    public void viderTampon() {
        tampon.clear();
    }
    
    public List<Message> lireTampon() {
        return tampon;
    }
    
    protected void notifierObservateurs() {
        super.setChanged();
        notifyObservers();
    }
    
}
