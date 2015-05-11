/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author Gwenole Lecorve
 */
public class StupideControleur implements ControleurInterface {
    
    protected String url;
    protected ConnecteurLocalInterface connecteur;
    
    public StupideControleur(String url, ConnecteurLocalInterface connecteur) {
        this.url = url;
        this.connecteur = connecteur;
    }
    
    @Override
    public void demanderSectionCritique() {
        System.out.println(url + ": Demande de section critique enregistrée");
        signalerAutorisation();
    }

    @Override
    public synchronized void signalerAutorisation() {
        System.out.println(url + ": Signalement de l'autorisation");
        connecteur.recevoirAutorisation();
    }

    @Override
    public void quitterSectionCritique() {
        System.out.println(url + ": Fin de section critique");
    }

    @Override
    public void enregistrerControleur(String urlDistant) {
        System.out.println(url + ": Enregistrement de " + urlDistant);
    }

    @Override
    public void oublierControleur(String urlDistant) {
        System.out.println(url + ": Oubli de " + urlDistant);
    }
    
}
