/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Classe interne pour l'affichage d'un message d'erreur
 *
 * @author Gwenole Lecorve
 */
class ErrorDialog {

    protected Component parentComponent;
    protected String title;
    protected String content;

    /**
     * Constructeur
     *
     * @param _parentComponent Composant parent de la boîte de dialogue
     * @param _title Titre
     * @param _content Détail du message d'erreur
     */
    protected ErrorDialog(Component _parentComponent, String _title, String _content) {
        parentComponent = _parentComponent;
        title = _title;
        content = _content;
        Thread t = new Thread(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(parentComponent,
                        content,
                        title,
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        t.start();
    }
}
