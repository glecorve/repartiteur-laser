/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Classe qui modelise le prisme, simplement a travers son orientation
 * @author Gwenole Lecorve
 */
public class Prisme {
    protected String depuisUrl;
    protected String versUrl;
    
    public void orienter(String _depuisUrl, String _versUrl) {
        this.depuisUrl = _depuisUrl;
        this.versUrl = _versUrl;
    }
    
    public String getDepuisUrl() {
        return depuisUrl;
    }
    
    public String getVersUrl() {
        return versUrl;
    }
}
