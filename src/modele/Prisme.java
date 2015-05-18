/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Observable;

/**
 * Classe qui modelise le prisme, simplement a travers son orientation
 * @author Gwenole Lecorve
 */
public class Prisme extends Observable {
    
    protected String depuisUrl;
    protected String versUrl;
    protected boolean transmissionEnCours;
    
    public void orienter(String _depuisUrl, String _versUrl) throws PrismeException {
        if (transmissionEnCours) {
            throw new PrismeException("Impossible de modifier l'orientation du prisme lorsqu'une transmission est en cours");
        }
        this.depuisUrl = _depuisUrl;
        this.versUrl = _versUrl;
        notifierObservateurs();
    }
    
    public String getDepuisUrl() throws PrismeException {
        if (depuisUrl == null) {
            throw new PrismeException("Le prisme n'est orienté vers aucun emetteur.");
        }
        return depuisUrl;
    }
    
    public String getVersUrl() throws PrismeException {
        if (versUrl == null) {
            throw new PrismeException("Le prisme n'est orienté vers aucun destinataire.");
        }
        return versUrl;
    }
    
    public boolean tranmissionEnCours() {
        return transmissionEnCours;
    }
    
    public void demarrerTransmission() {
        transmissionEnCours = true;
        notifierObservateurs();
    }
    
    public void stopperTransmission() {
        transmissionEnCours = false;
        notifierObservateurs();
    }
    
    protected void notifierObservateurs() {
        super.setChanged();
        notifyObservers();
    }
    
}
