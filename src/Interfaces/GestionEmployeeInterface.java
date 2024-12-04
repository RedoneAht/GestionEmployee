package Interfaces;

import java.util.List;

import ComponentEmployee.Employee;

public interface GestionEmployeeInterface {
	
	void ajouterEmployee(Employee e);

    void ajouterEmployee(int m, String nom, String prenom, String adresse);

    void supprimerEmployee(int m);

    void modifierEmployee(int m, String nom, String prenom, String adresse);

    boolean isExistEmployee(int m);

    Employee chercherEmployee(int m);

    void AfficherEmployees();
    
    public List<Employee> getAllEmployees();
}
