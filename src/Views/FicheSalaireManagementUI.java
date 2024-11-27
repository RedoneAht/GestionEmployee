package Views;
import javax.swing.*;

import ComponentEmployee.Employee;
import ComponentSalaire.FicheSalaire;
import ComponentSalaire.GestionSalaire;
import Interfaces.GestionEmployeeInterface;
import Interfaces.GestionSalaireInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class FicheSalaireManagementUI {
    private JFrame frame;
    private GestionSalaireInterface gestionFicheSalaire;
    private GestionEmployeeInterface gestionEmployee;
    int numFiche; 

    public FicheSalaireManagementUI() {
        gestionFicheSalaire = new GestionSalaire();
        frame = new JFrame("Gestion des Fiches de Salaire");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create components
        JTextField numFicheField = new JTextField(15);
        JTextField matriculeField = new JTextField(15);
        JTextField nbHeuresField = new JTextField(15);
        JTextField tauxHField = new JTextField(15);
        JTextField dateFichierField = new JTextField(15); // For date input

        JButton addButton = new JButton("Ajouter Fiche Salaire");
        JButton displayButton = new JButton("Afficher Fiches de Salaire");

        JTextArea displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);

        // Set up panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Numéro de Fiche:"));
        inputPanel.add(numFicheField);
        inputPanel.add(new JLabel("Matricule Employé:"));
        inputPanel.add(matriculeField);
        inputPanel.add(new JLabel("Nombre d'Heures:"));
        inputPanel.add(nbHeuresField);
        inputPanel.add(new JLabel("Taux Horaire:"));
        inputPanel.add(tauxHField);
        inputPanel.add(new JLabel("Date Fichier (YYYY-MM-DD):"));
        inputPanel.add(dateFichierField);
        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add button actions
        addButton.addActionListener(e -> {
            try {
                int numFiche = Integer.parseInt(numFicheField.getText());
                int matricule = Integer.parseInt(matriculeField.getText());
                int nbHeures = Integer.parseInt(nbHeuresField.getText());
                float tauxH = Float.parseFloat(tauxHField.getText());
                LocalDate dateFichier = LocalDate.parse(dateFichierField.getText());

                // Check if the employee exists
                if (!gestionEmployee.isExistEmployee(matricule)) {
                    JOptionPane.showMessageDialog(frame, "Erreur: L'employé avec le matricule " + matricule + " n'existe pas.");
                } else {
                    // Create the FicheSalaire object and add it
                    FicheSalaire fiche = new FicheSalaire(matricule, numFiche, dateFichier, nbHeures, tauxH);
                    gestionFicheSalaire.ajouterFicheSalaire(fiche);
                    JOptionPane.showMessageDialog(frame, "Fiche de salaire ajoutée avec succès!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout: " + ex.getMessage());
            }
        });


        displayButton.addActionListener(e -> {
            displayArea.setText(""); // Clear the area
            // Display fiches (similar logic as Employee)
            gestionFicheSalaire.afficherFicheSalaire(numFiche); // Modify as needed to return a string for display
        });

        frame.setVisible(true);
    }
}

