package Interfaces;

import java.util.List;

import ComponentSalaire.FicheSalaire;

public interface GestionSalaireInterface {

	void ajouterFicheSalaire(FicheSalaire fiche);

    void supprimerFicheSalaire(int numFiche);

    public void modifierFicheSalaire(int numFiche, int nbHeures, float tauxH, float taxes);
    
    void afficherFicheSalaire(int numFiche);
    
    public List<FicheSalaire> getAllFiches();
}
