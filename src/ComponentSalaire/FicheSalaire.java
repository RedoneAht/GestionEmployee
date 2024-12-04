package ComponentSalaire;

import java.time.LocalDate;

import ComponentEmployee.Employee;

public class FicheSalaire {
	private int matriculeEmployee;
	private int numFiche;
	private LocalDate dateF;
	private int nbHeures;
	private float tauxH;
	private float montantBrut;
	private float taxes;
	private float montantNet;
	
	public FicheSalaire(int matriculeEmployee,int numFiche, LocalDate dateF, int nbHeures, float tauxH , float taxes) {
		super();
		this.matriculeEmployee=matriculeEmployee;
		this.numFiche = numFiche;
		this.dateF = dateF;
		this.nbHeures = nbHeures;
		this.tauxH = tauxH;
		this.montantBrut = nbHeures * tauxH;
		this.taxes = taxes;
		this.montantNet = this.montantBrut - (this.montantBrut * this.taxes);
		
	}
	
	public int getMatriculeEmployee() {
		return matriculeEmployee;
	}
	public void setmatriculeEmployee(int matriculeEmployee) {
		this.matriculeEmployee = matriculeEmployee;
	}
	public int getNumFiche() {
		return numFiche;
	}
	public void setNumFiche(int numFiche) {
		this.numFiche = numFiche;
	}
	public LocalDate getDateF() {
		return dateF;
	}
	public void setDateF(LocalDate dateF) {
		this.dateF = dateF;
	}
	public int getNbHeures() {
		return nbHeures;
	}
	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}
	public float getTauxH() {
		return tauxH;
	}
	public void setTauxH(float tauxH) {
		this.tauxH = tauxH;
	}
	public float getMontantBrut() {
		return montantBrut;
	}
	public void setMontantBrut(float montantBrut) {
		this.montantBrut = montantBrut;
	}
	public float getTaxes() {
		return taxes;
	}
	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}
	public float getMontantNet() {
		return montantNet;
	}
	public void setMontantNet(float montantNet) {
		this.montantNet = montantNet;
	}
	
	
}
