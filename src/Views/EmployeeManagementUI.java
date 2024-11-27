package Views;
import javax.swing.*;

import ComponentEmployee.GestionEmployee;
import Interfaces.GestionEmployeeInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementUI {
    private JFrame frame;
    private GestionEmployeeInterface gestionEmployee;

    public EmployeeManagementUI() {
        gestionEmployee = new GestionEmployee();
        frame = new JFrame("Gestion des Employés");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create components
        JTextField matriculeField = new JTextField(15);
        JTextField nomField = new JTextField(15);
        JTextField prenomField = new JTextField(15);
        JTextField adresseField = new JTextField(15);

        JButton addButton = new JButton("Ajouter Employé");
        JButton displayButton = new JButton("Afficher Employés");

        JTextArea displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);

        // Set up panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Matricule:"));
        inputPanel.add(matriculeField);
        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(nomField);
        inputPanel.add(new JLabel("Prénom:"));
        inputPanel.add(prenomField);
        inputPanel.add(new JLabel("Adresse:"));
        inputPanel.add(adresseField);
        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add button actions
        addButton.addActionListener(e -> {
            try {
                int matricule = Integer.parseInt(matriculeField.getText());
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String adresse = adresseField.getText();
                
                // Check if the employee already exists
                if (gestionEmployee.isExistEmployee(matricule)) {
                    JOptionPane.showMessageDialog(frame, "Cet employé existe déjà!");
                } else {
                    gestionEmployee.ajouterEmployee(matricule, nom, prenom, adresse);
                    JOptionPane.showMessageDialog(frame, "Employé ajouté avec succès!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout: " + ex.getMessage());
            }
        });


        displayButton.addActionListener(e -> {
            displayArea.setText(""); // Clear the area
            gestionEmployee.AfficherEmployees(); // Here, we might also modify `AfficherEmployees` to return a string for easier display
        });

        frame.setVisible(true);
    }
}

