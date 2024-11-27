package main;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Views.EmployeeManagementUI;
import Views.FicheSalaireManagementUI;

public class Main {
    private JFrame frame;

    public Main() {
        frame = new JFrame("Gestion des Employés et Fiches de Salaire");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton manageEmployeesButton = new JButton("Gérer les Employés");
        JButton manageSalariesButton = new JButton("Gérer les Fiches de Salaire");

        manageEmployeesButton.addActionListener(e -> new EmployeeManagementUI());
        manageSalariesButton.addActionListener(e -> new FicheSalaireManagementUI());

        frame.add(manageEmployeesButton);
        frame.add(manageSalariesButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

