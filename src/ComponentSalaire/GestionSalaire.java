package ComponentSalaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionSalaire {

    // Liste en mémoire pour stocker les fiches de salaire
    private List<FicheSalaire> fichesSalaire;

    public GestionSalaire() {
        // Initialisation de la liste en mémoire
        fichesSalaire = new ArrayList<>();
    }

    // Ajouter une fiche de salaire
    public void ajouterFicheSalaire(FicheSalaire fiche) {
        fichesSalaire.add(fiche);
    }

    // Supprimer une fiche de salaire
    public void supprimerFicheSalaire(int numFiche) {
        fichesSalaire.removeIf(fiche -> fiche.getNumFiche() == numFiche);
    }

    // Modifier une fiche de salaire
    public void modifierFicheSalaire(int numFiche, int nbHeures, float tauxH, float taxes) {
        for (FicheSalaire fiche : fichesSalaire) {
            if (fiche.getNumFiche() == numFiche) {
                // Calcul des montants
                float montantBrut = nbHeures * tauxH;
                float montantNet = montantBrut - (montantBrut * taxes);

                // Mise à jour des valeurs
                fiche.setNbHeures(nbHeures);
                fiche.setTauxH(tauxH);
                fiche.setMontantBrut(montantBrut);
                fiche.setTaxes(taxes);
                fiche.setMontantNet(montantNet);
                break;
            }
        }
    }

    // Afficher les informations d'une fiche de salaire
    public void afficherFicheSalaire(int numFiche) {
        for (FicheSalaire fiche : fichesSalaire) {
            if (fiche.getNumFiche() == numFiche) {
                System.out.println("Informations de la fiche :");
                System.out.println("Numéro de fiche: " + fiche.getNumFiche());
                System.out.println("Matricule: " + fiche.getMatriculeEmployee());
                System.out.println("Date de fiche: " + fiche.getDateF());
                System.out.println("Nombre d'heures: " + fiche.getNbHeures());
                System.out.println("Taux horaire: " + fiche.getTauxH());
                System.out.println("Montant brut: " + fiche.getMontantBrut());
                System.out.println("Taxes: " + fiche.getTaxes());
                System.out.println("Montant net: " + fiche.getMontantNet());
                break;
            }
        }
    }

    // Récupérer toutes les fiches de salaire
    public List<FicheSalaire> getAllFiches() {
        return new ArrayList<>(fichesSalaire);
    }

    // Vérifier si une fiche de salaire existe
    public boolean isExistFicheSalaire(int numFiche) {
        return fichesSalaire.stream().anyMatch(fiche -> fiche.getNumFiche() == numFiche);
    }
}
