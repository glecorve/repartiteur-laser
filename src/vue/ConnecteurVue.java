/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controle.ConnecteurLocalInterface;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import modele.Annuaire;
import modele.Connecteur;
import modele.Message;

/**
 *
 * @author Gwenole Lecorve
 */
public class ConnecteurVue extends JFrame implements Observer {

    private ConnecteurLocalInterface backend;
    private boolean emettreEnBoucle;

    /**
     * Creates new form ConnecteurOptiqueFenetre
     *
     * @param backend Traitements en arri�re-plan
     * @param connecteur Connecteur
     */
    public ConnecteurVue(ConnecteurLocalInterface backend, Connecteur connecteur) {
        this.backend = backend;
        this.emettreEnBoucle = false;

        initComponents();

        setResizable(false);
        setTitle("Connecteur optique - " + backend.getUrl());
        urlTextField.setText(backend.getUrl());
        etatLabel.setText("<html><a color=red>D�connect�</a></html>");

        emettreBouton.setEnabled(false);
        demarrerToggleBouton.setEnabled(false);
        stopperBouton.setEnabled(false);
        destinataireList.setEnabled(false);
        
        DefaultCaret caret = (DefaultCaret)receptionTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        connecteur.addObserver(this);
        backend.getAnnuaire().addObserver(this);

        setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        receptionPanel = new javax.swing.JPanel();
        receptionScrollPane = new javax.swing.JScrollPane();
        receptionTextPane = new javax.swing.JTextPane();
        emissionPanel = new javax.swing.JPanel();
        emettreBouton = new javax.swing.JButton();
        emissionScrollPane = new javax.swing.JScrollPane();
        emissionTextArea = new javax.swing.JTextArea();
        listeScrollPane = new javax.swing.JScrollPane();
        destinataireList = new javax.swing.JList();
        demarrerToggleBouton = new javax.swing.JToggleButton();
        stopperBouton = new javax.swing.JButton();
        statutPanel = new javax.swing.JPanel();
        etatLabel = new javax.swing.JLabel();
        connectionBouton = new javax.swing.JButton();
        urlTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        receptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "R�ception", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        receptionTextPane.setContentType("text/html"); // NOI18N
        receptionTextPane.setDocument(new HTMLDocument());
        receptionScrollPane.setViewportView(receptionTextPane);

        javax.swing.GroupLayout receptionPanelLayout = new javax.swing.GroupLayout(receptionPanel);
        receptionPanel.setLayout(receptionPanelLayout);
        receptionPanelLayout.setHorizontalGroup(
            receptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(receptionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        receptionPanelLayout.setVerticalGroup(
            receptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(receptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        emissionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�mission", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        emettreBouton.setText("�mettre une fois");
        emettreBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emettreBoutonActionPerformed(evt);
            }
        });

        emissionTextArea.setColumns(20);
        emissionTextArea.setRows(5);
        emissionTextArea.setText("Message laser");
        emissionTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emissionTextAreaKeyReleased(evt);
            }
        });
        emissionScrollPane.setViewportView(emissionTextArea);

        destinataireList.setModel(new SortedListModel());
        destinataireList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        destinataireList.setEnabled(false);
        listeScrollPane.setViewportView(destinataireList);

        demarrerToggleBouton.setText("D�marrer l'�mission");
        demarrerToggleBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demarrerToggleBoutonActionPerformed(evt);
            }
        });

        stopperBouton.setText("Stopper l'�mission");
        stopperBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopperBoutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout emissionPanelLayout = new javax.swing.GroupLayout(emissionPanel);
        emissionPanel.setLayout(emissionPanelLayout);
        emissionPanelLayout.setHorizontalGroup(
            emissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emissionPanelLayout.createSequentialGroup()
                .addComponent(listeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emettreBouton, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(demarrerToggleBouton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopperBouton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(emissionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );
        emissionPanelLayout.setVerticalGroup(
            emissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, emissionPanelLayout.createSequentialGroup()
                .addComponent(emissionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(emissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, emissionPanelLayout.createSequentialGroup()
                        .addComponent(emettreBouton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(demarrerToggleBouton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopperBouton)
                        .addGap(6, 6, 6))
                    .addComponent(listeScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        statutPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statut", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        etatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etatLabel.setText("Non connect�");

        connectionBouton.setText("Connecter une fibre optique");
        connectionBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionBoutonActionPerformed(evt);
            }
        });

        urlTextField.setEditable(false);
        urlTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        urlTextField.setText("rmi://url");

        javax.swing.GroupLayout statutPanelLayout = new javax.swing.GroupLayout(statutPanel);
        statutPanel.setLayout(statutPanelLayout);
        statutPanelLayout.setHorizontalGroup(
            statutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(urlTextField)
                    .addGroup(statutPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(etatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        statutPanelLayout.setVerticalGroup(
            statutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statutPanelLayout.createSequentialGroup()
                .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(statutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etatLabel)
                    .addComponent(connectionBouton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(receptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(emissionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(statutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emissionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void connectionBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionBoutonActionPerformed
        if (backend.estConnecte()) {
            try {
                backend.deconnecterFibreOptique();
                etatLabel.setText("<html><a color=red>D�connect�</a></html>");
                connectionBouton.setText("Connecter une fibre optique");
                if (emettreEnBoucle) {
                    emettreEnBoucle = false;
                    afficherErreur("Arret de l'emission", "L'envoi en boucle d'un message a ete interrompu en raison d'une deconnexion.");
                }
                ajusterEtatBoutons();
            } catch (Exception ex) {
                afficherErreur("Erreur de d�connexion", ex.getMessage());
            }
        } else {
            try {
                backend.connecterFibreOptique();
                etatLabel.setText("<html><a color=green>Connect�</a></html>");
                connectionBouton.setText("D�connecter la fibre optique");
                ajusterEtatBoutons();
            } catch (Exception ex) {
                afficherErreur("Erreur de d�connexion", ex.getMessage());
            }
        }
    }//GEN-LAST:event_connectionBoutonActionPerformed

    private void emettreBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emettreBoutonActionPerformed
        try {
            backend.emettreMessage((String) destinataireList.getSelectedValue(), emissionTextArea.getText());
        } catch (Exception ex) {
            afficherErreur("Erreur lors de l'emission du message", ex.getMessage());
        }
    }//GEN-LAST:event_emettreBoutonActionPerformed

    private void demarrerToggleBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demarrerToggleBoutonActionPerformed
        emettreEnBoucle = true;
        Thread t;
        t = new EmissionThread(this);
        t.start();
        ajusterEtatBoutons();
    }//GEN-LAST:event_demarrerToggleBoutonActionPerformed

    private void stopperBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopperBoutonActionPerformed
        emettreEnBoucle = false;
        ajusterEtatBoutons();
    }//GEN-LAST:event_stopperBoutonActionPerformed

    private void emissionTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emissionTextAreaKeyReleased
        if (emissionTextArea.getText().equals("")) {
            afficherErreur("Arret de l'emission", "L'envoi en boucle d'un message a ete interrompu en raison d'un message vide.");
            emettreEnBoucle = false;
        }
        ajusterEtatBoutons();
    }//GEN-LAST:event_emissionTextAreaKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            backend.deconnecterFibreOptique();
        } catch (Exception ex) {
            afficherErreur("Erreur lors de la fermeture", ex.getMessage());
        }
    }//GEN-LAST:event_formWindowClosing

    protected void ajusterEtatBoutons() {
        // Non connecte ou message a envoyer vide ou aucun destinataire existant
        if (!backend.estConnecte() || emissionTextArea.getText().equals("") || destinataireList.getModel().getSize() == 0) {
            emettreBouton.setEnabled(false);
            demarrerToggleBouton.setSelected(false);
            demarrerToggleBouton.setEnabled(false);
            stopperBouton.setEnabled(false);
            destinataireList.setEnabled(false);
        } // Connecte et message a envoyer non vide et au moins un destinataire et envoi en boucle actif
        else if (emettreEnBoucle) {
            emettreBouton.setEnabled(false);
            demarrerToggleBouton.setSelected(true);
            demarrerToggleBouton.setEnabled(false);
            stopperBouton.setEnabled(true);
            destinataireList.setEnabled(false);
        } // Connecte et message a envoyer non vide et au moins un destinataire et envoi en boucle non actif
        else {
            emettreBouton.setEnabled(true);
            demarrerToggleBouton.setSelected(false);
            demarrerToggleBouton.setEnabled(true);
            stopperBouton.setEnabled(false);
            destinataireList.setEnabled(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectionBouton;
    private javax.swing.JToggleButton demarrerToggleBouton;
    private javax.swing.JList destinataireList;
    private javax.swing.JButton emettreBouton;
    private javax.swing.JPanel emissionPanel;
    private javax.swing.JScrollPane emissionScrollPane;
    private javax.swing.JTextArea emissionTextArea;
    private javax.swing.JLabel etatLabel;
    private javax.swing.JScrollPane listeScrollPane;
    private javax.swing.JPanel receptionPanel;
    private javax.swing.JScrollPane receptionScrollPane;
    private javax.swing.JTextPane receptionTextPane;
    private javax.swing.JPanel statutPanel;
    private javax.swing.JButton stopperBouton;
    private javax.swing.JTextField urlTextField;
    // End of variables declaration//GEN-END:variables

    protected void remplirListe(Annuaire annuaire) {
        String selection = (String) destinataireList.getSelectedValue();
        destinataireList.removeAll();
        SortedListModel listModel = (SortedListModel) destinataireList.getModel();
        listModel.clear();
        for (String url : annuaire.getConnecteursDistants().keySet()) {
            listModel.add(url);
        }
        destinataireList.setModel(listModel);
        
        int selectionNouvelIndex;
        try {
            selectionNouvelIndex = listModel.getElementIndex(selection);
            destinataireList.setSelectedIndex(selectionNouvelIndex);
        } catch (Exception ex) {
            if (selection == null || listModel.getSize() > 0) {
                destinataireList.setSelectedIndex(0);
            }
        }
        ajusterEtatBoutons();
    }

    protected void ajouterTexteRecu(String texte) {
        try {
            HTMLDocument doc = (HTMLDocument) receptionTextPane.getDocument();
            ((HTMLEditorKit) receptionTextPane.getEditorKitForContentType("text/html")).insertHTML(doc, doc.getLength(), texte, 0, 0, null);
        } catch (Exception ex) {
            afficherErreur("Erreur lors de l'affichage d'un message recu", ex.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Connecteur) {
            for (Message message : ((Connecteur) o).lireTampon()) {
                ajouterTexteRecu(message.toHTML());
            }
            ((Connecteur) o).viderTampon();
        } else if (o instanceof Annuaire) {
            remplirListe((Annuaire) o);
        }
        repaint();
    }

    /**
     * Affiche une bo�te de dialogue correspondant � une erreur
     *
     * @param titre Titre de la bo�te de dialogue
     * @param contenu D�tail du message d'erreur
     */
    public void afficherErreur(String titre, String contenu) {
        new ErrorDialog(this, titre, contenu);
    }


    protected class EmissionThread extends Thread {

        JFrame parent;

        EmissionThread(JFrame parent) {
            this.parent = parent;
        }

        public void run() {
            while (emettreEnBoucle && destinataireList.getSelectedValue() != null) {
                try {
                    backend.emettreMessage((String) destinataireList.getSelectedValue(), emissionTextArea.getText());
                } catch (Exception ex) {
                    new ErrorDialog(parent, "Erreur lors de l'emission du message", ex.getMessage());
                }
            }
        }
    }
}
