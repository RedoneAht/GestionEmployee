package ComponentEmployee;

import java.util.ArrayList;
import java.util.List;

public class GestionEmployee {

    // Liste en mémoire pour stocker les employés
    private List<Employee> employees;

    public GestionEmployee() {
        // Initialisation de la liste en mémoire
        employees = new ArrayList<>();
    }

    // Ajouter un employé
    public void ajouterEmployee(Employee e) {
        employees.add(e);
    }

    // Ajouter un employé en utilisant des paramètres
    public void ajouterEmployee(int m, String nom, String prenom, String adresse) {
        Employee e = new Employee(m, nom, prenom, adresse);
        employees.add(e);
    }

    // Supprimer un employé
    public void supprimerEmployee(int matricule) {
        employees.removeIf(e -> e.getMatricule() == matricule);
    }

    // Modifier un employé
    public void modifierEmployee(int m, String nom, String prenom, String adresse) {
        for (Employee e : employees) {
            if (e.getMatricule() == m) {
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setAdresse(adresse);
                break;
            }
        }
    }

    // Vérifier si un employé existe
    public boolean isExistEmployee(int m) {
        return employees.stream().anyMatch(e -> e.getMatricule() == m);
    }

    // Chercher un employé par matricule
    public Employee chercherEmployee(int m) {
        for (Employee e : employees) {
            if (e.getMatricule() == m) {
                return e;
            }
        }
        return null;
    }

    // Obtenir tous les employés
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    // Afficher tous les employés
    public void AfficherEmployees() {
        System.out.println("La liste des employés:");
        for (Employee e : employees) {
            System.out.println("Matricule: " + e.getMatricule());
            System.out.println("Nom: " + e.getNom());
            System.out.println("Prenom: " + e.getPrenom());
            System.out.println("Adresse: " + e.getAdresse());
            System.out.println("------------------------");
        }
    }
}
