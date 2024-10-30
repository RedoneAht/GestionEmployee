package main;

import java.time.LocalDate;
import java.util.Scanner;

import ComponentEmployee.Employee;
import ComponentEmployee.GestionEmployee;
import ComponentSalaire.FicheSalaire;
import ComponentSalaire.GestionSalaire;

public class Main {
	
	static public void main(String [] args) {
		
		GestionEmployee ge = new GestionEmployee();
		GestionSalaire gs = new GestionSalaire();
		
		Scanner input = new Scanner(System.in);

		System.out.println("Entrer le matricule:");
		int matricule = input.nextInt();
		
		System.out.println("Entrer le nom:");
		String nom = input.next();
		
		System.out.println("Entrer le prenom:");
		String prenom = input.next();
		
		System.out.println("Entrer ladresse:");
		String adresse = input.next();
		
		Employee e = new Employee(matricule,nom,prenom,adresse);
		FicheSalaire fiche = new FicheSalaire(e, 1, LocalDate.now(), 40, 15.0f);
		
		ge.ajouterEmployee(e);
		ge.AfficherEmployees();
		
		gs.calculerSalaire(fiche);  

        gs.afficherFicheSalaire(fiche);
     
		
	}
}
