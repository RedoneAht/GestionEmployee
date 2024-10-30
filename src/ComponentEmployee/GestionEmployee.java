package ComponentEmployee;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionEmployee {
	private ArrayList<Employee> ListEmployees;
	
	public GestionEmployee() {
		ListEmployees =new ArrayList<Employee>();
	}

	public void ajouterEmployee(Employee e) {
		ListEmployees.add(e);
	}
	
	public void ajouterEmployee(int m,String nom,String prenom,String adresse) {
		Employee e = new Employee(m,nom,prenom,adresse);
		ListEmployees.add(e);
	}
	
	public void supprimerEmployee(Employee e) {
		ListEmployees.remove(e);
	}
	
	public void modifierEmployee(Employee e,String nom,String prenom,String adresse) {
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setAdresse(adresse);
	} 
	
	public boolean isExistEmployee(int m) {
		for (Employee employee : ListEmployees) {
			if(employee.getMatricule()==m)
				return true;	
		}
		return false;
	}
	
	public Employee chercherEmployee(int m) {
		for (Employee employee : ListEmployees) {
			if(employee.getMatricule()==m)
				return employee;	
		}
		return null;	
	}
	
	public void AfficherEmployees() {
		System.out.println("La liste des employees:");
		for (Employee employee : ListEmployees) {
			System.out.println("Matricule:" + employee.getMatricule());
			System.out.println("nom:" + employee.getNom());
			System.out.println("prenom:" + employee.getPrenom());
			System.out.println("adresse:" + employee.getAdresse());
		}
	}
	
}
