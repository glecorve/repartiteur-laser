/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Classe pour les exceptions emanant du prisme
 * @author Gwenole Lecorve
 */
public class PrismeException extends Exception {

    public PrismeException() {
        super();
    }
    
    public PrismeException(String message) {
        super(message);
    }
    
}
