package Interfaces;

import ComponentSalaire.FicheSalaire;

public interface GestionSalaireInterface {

	void ajouterFicheSalaire(FicheSalaire fiche);

    void supprimerFicheSalaire(int numFiche);

    void modifierFicheSalaire(int numFiche, double nbHeures, double tauxH, double montantBrut, double taxes, double montantNet);

    void afficherFicheSalaire(int numFiche);
}
