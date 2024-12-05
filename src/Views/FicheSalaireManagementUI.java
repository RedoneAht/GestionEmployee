package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ComponentEmployee.GestionEmployee;
import ComponentSalaire.FicheSalaire;
import ComponentSalaire.GestionSalaire;
import Interfaces.GestionEmployeeInterface;
import Interfaces.GestionSalaireInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class FicheSalaireManagementUI extends JFrame {
    private GestionSalaireInterface gestionFicheSalaire;
    private GestionEmployeeInterface gestionEmployee;

    // Composants
    private JTextField txtNumFiche, txtMatricule, txtNbHeures, txtTauxH, txtTaxes, txtDateF;
    private JButton btnAjouterModifier, btnSupprimer;
    private JTable table;
    private DefaultTableModel model;

    private int currentNumFiche = -1;  // Pour gérer la modification d'une fiche existante

    public FicheSalaireManagementUI() {
        gestionFicheSalaire = new GestionSalaire();
        gestionEmployee = new GestionEmployee();

        // Configuration de la fenêtre
        setTitle("Gestion des Fiches de Salaire");
        setSize(800, 600);
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel pour le formulaire
        JPanel panelFormEtBouton = new JPanel();
        panelFormEtBouton.setLayout(new BoxLayout(panelFormEtBouton, BoxLayout.Y_AXIS));

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Détails de la Fiche Salaire"));

        panelForm.add(new JLabel("Numéro de Fiche:"));
        txtNumFiche = new JTextField();
        panelForm.add(txtNumFiche);

        panelForm.add(new JLabel("Matricule Employé:"));
        txtMatricule = new JTextField();
        panelForm.add(txtMatricule);

        panelForm.add(new JLabel("Nombre d'Heures:"));
        txtNbHeures = new JTextField();
        panelForm.add(txtNbHeures);

        panelForm.add(new JLabel("Taux Horaire:"));
        txtTauxH = new JTextField();
        panelForm.add(txtTauxH);

        panelForm.add(new JLabel("Taxes (Taux):"));
        txtTaxes = new JTextField();
        panelForm.add(txtTaxes);

        panelForm.add(new JLabel("Date de la Fiche (YYYY-MM-DD):"));
        txtDateF = new JTextField();
        panelForm.add(txtDateF);

        panelFormEtBouton.add(panelForm);
        
        // Panel pour le bouton Ajouter/Modifier
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAjouterModifier = new JButton("Ajouter");
        panelButton.add(btnAjouterModifier);
        panelFormEtBouton.add(panelButton);

        // Placer le panelButton dans le BorderLayout.NORTH
        add(panelFormEtBouton, BorderLayout.NORTH);

        // Tableau pour afficher les fiches de salaire
        model = new DefaultTableModel(new String[]{
            "Numéro de Fiche", "Matricule", "Nombre d'Heures", "Taux Horaire", "Taxes", "Date", "Montant Brut", "Montant Net"
        }, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajouter le tableau dans la partie centrale de la fenêtre (CENTER)
        add(scrollPane, BorderLayout.CENTER);

        // Panel pour le bouton Supprimer
        JPanel panelDeleteButton = new JPanel(new FlowLayout());
        btnSupprimer = new JButton("Supprimer");
        panelDeleteButton.add(btnSupprimer);

        // Ajouter le bouton Supprimer dans la partie inférieure de la fenêtre (SOUTH)
        add(panelDeleteButton, BorderLayout.SOUTH);

        // Ajouter l'action de l'ajout ou modification
        btnAjouterModifier.addActionListener(this::ajouterOuModifierFiche);

        // Ajouter l'action de suppression
        btnSupprimer.addActionListener(this::supprimerFiche);

        // Ajouter un écouteur de sélection pour charger les informations dans le formulaire
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Récupérer les informations de la fiche sélectionnée
                int numFiche = (Integer) model.getValueAt(selectedRow, 0);
                int matricule = (Integer) model.getValueAt(selectedRow, 1);
                int nbHeures = (Integer) model.getValueAt(selectedRow, 2);
                float tauxH = (Float) model.getValueAt(selectedRow, 3);
                float taxes = (Float) model.getValueAt(selectedRow, 4);
                LocalDate dateF = (LocalDate) model.getValueAt(selectedRow, 5);

                // Remplir le formulaire avec les informations de la fiche
                txtNumFiche.setText(String.valueOf(numFiche));
                txtMatricule.setText(String.valueOf(matricule));
                txtNbHeures.setText(String.valueOf(nbHeures));
                txtTauxH.setText(String.valueOf(tauxH));
                txtTaxes.setText(String.valueOf(taxes));
                txtDateF.setText(dateF.toString());

                // Modifier le texte du bouton pour indiquer une modification
                btnAjouterModifier.setText("Modifier");
                currentNumFiche = numFiche;  // Conserver le numéro de la fiche pour la modification
            }
        });

        // Charger les fiches de salaire dès le démarrage
        afficherFiches();
    }

    // Méthode pour ajouter ou modifier une fiche de salaire
    private void ajouterOuModifierFiche(ActionEvent e) {
        try {
        	
        	if (txtNumFiche.getText().isEmpty() || txtMatricule.getText().isEmpty() || 
                    txtNbHeures.getText().isEmpty() || txtTauxH.getText().isEmpty() || 
                    txtTaxes.getText().isEmpty() || txtDateF.getText().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(this, "Erreur : Tous les champs doivent être remplis.");
                    return;  // Ne pas continuer si un champ est vide
                }
        	 
        	
            int numFiche = Integer.parseInt(txtNumFiche.getText());
            int matricule = Integer.parseInt(txtMatricule.getText());
            int nbHeures = Integer.parseInt(txtNbHeures.getText());
            float tauxH = Float.parseFloat(txtTauxH.getText());
            float taxes = Float.parseFloat(txtTaxes.getText());
            LocalDate dateF = LocalDate.parse(txtDateF.getText());
            
            boolean ficheExists = gestionFicheSalaire.isExistFicheSalaire(numFiche);
	       	 if(ficheExists == true) {
	       		JOptionPane.showMessageDialog(this, "Erreur : Matricule existe.");
	       		return;
	       	 }

            // Vérifier si l'employé existe
            if (!gestionEmployee.isExistEmployee(matricule)) {
                JOptionPane.showMessageDialog(this, "Erreur : L'employé avec le matricule " + matricule + " n'existe pas.");
                return;
            }

            FicheSalaire fiche = new FicheSalaire(matricule, numFiche, dateF, nbHeures, tauxH, taxes);

            if (currentNumFiche == -1) {
                // Ajouter une nouvelle fiche
                gestionFicheSalaire.ajouterFicheSalaire(fiche);
                JOptionPane.showMessageDialog(this, "Fiche ajoutée avec succès !");
            } else {
                // Modifier une fiche existante
                fiche.setNumFiche(currentNumFiche);
                gestionFicheSalaire.modifierFicheSalaire(currentNumFiche, nbHeures, tauxH, taxes);
                JOptionPane.showMessageDialog(this, "Fiche modifiée avec succès !");
            }

            // Réinitialiser et afficher les fiches
            resetForm();
            afficherFiches();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    // Méthode pour afficher toutes les fiches de salaire
    private void afficherFiches() {
        model.setRowCount(0); // Effacer les anciennes lignes

        List<FicheSalaire> fiches = gestionFicheSalaire.getAllFiches();
        for (FicheSalaire fiche : fiches) {
            model.addRow(new Object[]{
                fiche.getNumFiche(),
                fiche.getMatriculeEmployee(),
                fiche.getNbHeures(),
                fiche.getTauxH(),
                fiche.getTaxes(),
                fiche.getDateF(),
                fiche.getMontantBrut(),
                fiche.getMontantNet()
            });
        }
    }

    // Méthode pour supprimer une fiche de salaire
    private void supprimerFiche(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int numFiche = (Integer) model.getValueAt(selectedRow, 0);
            gestionFicheSalaire.supprimerFicheSalaire(numFiche);
            JOptionPane.showMessageDialog(this, "Fiche supprimée avec succès !");
            afficherFiches(); // Mettre à jour le tableau
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une fiche à supprimer.");
        }
    }

    // Réinitialiser le formulaire
    private void resetForm() {
        txtNumFiche.setText("");
        txtMatricule.setText("");
        txtNbHeures.setText("");
        txtTauxH.setText("");
        txtTaxes.setText("");
        txtDateF.setText("");
        btnAjouterModifier.setText("Ajouter");
        currentNumFiche = -1;  // Réinitialiser pour une nouvelle fiche
    }
}
