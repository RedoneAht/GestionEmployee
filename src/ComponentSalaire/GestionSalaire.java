package ComponentSalaire;

public class GestionSalaire {
	
	public void calculerSalaire(FicheSalaire fiche) {
        float montantBrut = fiche.getNbHeures() * fiche.getTauxH();
        float taxes = montantBrut * 0.2f;
        float montantNet = montantBrut - taxes;
        
        fiche.setMontantBrut(montantBrut);
        fiche.setTaxes(taxes);
        fiche.setMontantNet(montantNet);
    }
	
public void afficherFicheSalaire(FicheSalaire fiche) {
		
		System.out.println("Informations de l'employé :");
        System.out.println("Matricule: " + fiche.getE().getMatricule());
        System.out.println("Nom: " + fiche.getE().getNom() + " " + fiche.getE().getPrenom());
        System.out.println("Numéro de fiche: " + fiche.getNumFiche());
        System.out.println("Date de fiche: " + fiche.getDateF());
        System.out.println("Nombre d'heures: " + fiche.getNbHeures());
        System.out.println("Taux horaire: " + fiche.getTauxH());
        System.out.println("Montant brut: " + fiche.getMontantBrut());
        System.out.println("Taxes: " + fiche.getTaxes());
        System.out.println("Montant net: " + fiche.getMontantNet());
    }
}
