package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ComponentEmployee.Employee;
import ComponentEmployee.GestionEmployee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmployeeManagementUI extends JFrame {

    private JTextField txtId, txtNom, txtPrenom, txtAdresse;
    private JButton btnAjouter, btnSupprimer;
    private JTable table;
    private DefaultTableModel model;

    private GestionEmployee gestionEmployee;
    private Integer selectedEmployeeId = null;

    public EmployeeManagementUI() {
        gestionEmployee = new GestionEmployee();

        setTitle("Gestion des Employés");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormEtBouton = new JPanel();
        panelFormEtBouton.setLayout(new BoxLayout(panelFormEtBouton, BoxLayout.Y_AXIS));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Informations de l'employé"));

        panelForm.add(new JLabel("Matricule :"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nom :"));
        txtNom = new JTextField();
        panelForm.add(txtNom);

        panelForm.add(new JLabel("Prénom :"));
        txtPrenom = new JTextField();
        panelForm.add(txtPrenom);

        panelForm.add(new JLabel("Adresse :"));
        txtAdresse = new JTextField();
        panelForm.add(txtAdresse);

        panelFormEtBouton.add(panelForm);

        JPanel panelBoutonAjouter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAjouter = new JButton("Ajouter");
        panelBoutonAjouter.add(btnAjouter);
        panelFormEtBouton.add(panelBoutonAjouter);

        add(panelFormEtBouton, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Matricule", "Nom", "Prénom", "Adresse"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout());
        btnSupprimer = new JButton("Supprimer");
        panelButtons.add(btnSupprimer);
        add(panelButtons, BorderLayout.SOUTH);

        btnAjouter.addActionListener(this::ajouterOuModifierEmploye);
        btnSupprimer.addActionListener(this::supprimerEmploye);

        afficherEmployes();

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                selectedEmployeeId = (Integer) model.getValueAt(selectedRow, 0);
                afficherEmployeDansFormulaire(selectedRow);
                btnAjouter.setText("Modifier");
            }
        });
    }

    private void afficherEmployeDansFormulaire(int row) {
        txtId.setText(String.valueOf(model.getValueAt(row, 0)));
        txtNom.setText((String) model.getValueAt(row, 1));
        txtPrenom.setText((String) model.getValueAt(row, 2));
        txtAdresse.setText((String) model.getValueAt(row, 3));
    }

    private void ajouterOuModifierEmploye(ActionEvent e) {
        int matricule;
        try {
            matricule = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le matricule doit être un entier valide.");
            return;
        }
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String adresse = txtAdresse.getText();

        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.");
            return;
        }

        try {
            if (btnAjouter.getText().equals("Ajouter")) {
                if (gestionEmployee.isExistEmployee(matricule)) {
                    JOptionPane.showMessageDialog(this, "Le matricule saisi existe déjà.");
                    return;
                }
                gestionEmployee.ajouterEmployee(matricule, nom, prenom, adresse);
                JOptionPane.showMessageDialog(this, "Employé ajouté avec succès !");
            } else {
                if (selectedEmployeeId != null && !selectedEmployeeId.equals(matricule) && gestionEmployee.isExistEmployee(matricule)) {
                    JOptionPane.showMessageDialog(this, "Le nouveau matricule existe déjà.");
                    return;
                }
                gestionEmployee.modifierEmployee(matricule, nom, prenom, adresse);
                JOptionPane.showMessageDialog(this, "Employé modifié avec succès !");
                selectedEmployeeId = null;
                btnAjouter.setText("Ajouter");
            }
            afficherEmployes();
            viderChamps();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    private void supprimerEmploye(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int matricule = (Integer) model.getValueAt(selectedRow, 0);
            try {
                gestionEmployee.supprimerEmployee(matricule);
                JOptionPane.showMessageDialog(this, "Employé supprimé avec succès !");
                afficherEmployes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne.");
        }
    }

    private void afficherEmployes() {
        model.setRowCount(0);
        List<Employee> employees = gestionEmployee.getAllEmployees();
        for (Employee employee : employees) {
            model.addRow(new Object[]{
                employee.getMatricule(),
                employee.getNom(),
                employee.getPrenom(),
                employee.getAdresse()
            });
        }
    }

    private void viderChamps() {
        txtId.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtAdresse.setText("");
    }
}
