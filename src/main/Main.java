package main;

import java.time.LocalDate;
import java.util.Scanner;

import ComponentEmployee.GestionEmployee;
import ComponentSalaire.FicheSalaire;
import ComponentSalaire.GestionSalaire;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionEmployee gestionEmployee = new GestionEmployee();
        GestionSalaire gestionSalaire = new GestionSalaire();

        while (true) {
            System.out.println("----- Menu -----");
            System.out.println("1. Gestion des employés");
            System.out.println("2. Gestion des fiches de salaire");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    gestionDesEmployes(scanner, gestionEmployee);
                    break;
                case 2:
                    gestionDesFichesSalaire(scanner, gestionSalaire, gestionEmployee);
                    break;
                case 3:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    private static void gestionDesEmployes(Scanner scanner, GestionEmployee gestionEmployee) {
        while (true) {
            System.out.println("\n--- Gestion des Employés ---");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Supprimer un employé");
            System.out.println("3. Modifier un employé");
            System.out.println("4. Afficher tous les employés");
            System.out.println("5. Revenir au menu principal");
            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme la ligne vide

            switch (choix) {
                case 1:
                    System.out.print("Entrez le matricule: ");
                    int matricule = scanner.nextInt();
                    if (gestionEmployee.isExistEmployee(matricule)) {
                        System.out.println("L'employé avec ce matricule existe déjà.");
                    } else {
                        scanner.nextLine();  // Consomme la ligne vide
                        System.out.print("Entrez le nom: ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez le prénom: ");
                        String prenom = scanner.nextLine();
                        System.out.print("Entrez l'adresse: ");
                        String adresse = scanner.nextLine();
                        gestionEmployee.ajouterEmployee(matricule, nom, prenom, adresse);
                        System.out.println("Employé ajouté avec succès!");
                    }
                    break;
                case 2:
                    System.out.print("Entrez le matricule de l'employé à supprimer: ");
                    int matriculeSupp = scanner.nextInt();
                    if (gestionEmployee.isExistEmployee(matriculeSupp)) {
	                    gestionEmployee.supprimerEmployee(matriculeSupp);
	                    System.out.println("Employé supprimé avec succès!");
                    } else {
                        System.out.println("L'employé avec ce matricule n'existe pas.");
                    }
                    break;
                case 3:
                    System.out.print("Entrez le matricule de l'employé à modifier: ");
                    int matriculeMod = scanner.nextInt();
                    scanner.nextLine();  // Consomme la ligne vide
                    if (gestionEmployee.isExistEmployee(matriculeMod)) {
                        System.out.print("Entrez le nouveau nom: ");
                        String nouveauNom = scanner.nextLine();
                        System.out.print("Entrez le nouveau prénom: ");
                        String nouveauPrenom = scanner.nextLine();
                        System.out.print("Entrez la nouvelle adresse: ");
                        String nouvelleAdresse = scanner.nextLine();
                        gestionEmployee.modifierEmployee(matriculeMod, nouveauNom, nouveauPrenom, nouvelleAdresse);
                        System.out.println("Employé modifié avec succès!");
                    } else {
                        System.out.println("L'employé avec ce matricule n'existe pas.");
                    }
                    break;
                case 4:
                    gestionEmployee.AfficherEmployees();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    private static void gestionDesFichesSalaire(Scanner scanner, GestionSalaire gestionSalaire, GestionEmployee gestionEmployee) {
        while (true) {
            System.out.println("\n--- Gestion des Fiches de Salaire ---");
            System.out.println("1. Ajouter une fiche de salaire");
            System.out.println("2. Supprimer une fiche de salaire");
            System.out.println("3. Modifier une fiche de salaire");
            System.out.println("4. Afficher toutes les fiches de salaire");
            System.out.println("5. Revenir au menu principal");
            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("Entrez le numéro de la fiche: ");
                    int numFiche = scanner.nextInt();
                    if (gestionSalaire.isExistFicheSalaire(numFiche)) {
                        System.out.println("La fiche de salaire avec ce numéro existe déjà.");
                    } else {
                        System.out.print("Entrez le matricule de l'employé: ");
                        int matriculeEmployee = scanner.nextInt();
                        if (!gestionEmployee.isExistEmployee(matriculeEmployee)) {
                            System.out.println("L'employé avec ce matricule n'existe pas.");
                        } else {
                            System.out.print("Entrez la date (YYYY-MM-DD): ");
                            String date = scanner.next();
                            LocalDate dateF = LocalDate.parse(date);
                            System.out.print("Entrez le nombre d'heures: ");
                            int nbHeures = scanner.nextInt();
                            System.out.print("Entrez le taux horaire: ");
                            float tauxH = scanner.nextFloat();
                            System.out.print("Entrez les taxes: ");
                            float taxes = scanner.nextFloat();
                            FicheSalaire fiche = new FicheSalaire(matriculeEmployee, numFiche, dateF, nbHeures, tauxH, taxes);
                            gestionSalaire.ajouterFicheSalaire(fiche);
                            System.out.println("Fiche de salaire ajoutée avec succès!");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Entrez le numéro de la fiche à supprimer: ");
                    int numFicheSupp = scanner.nextInt();
                    if (gestionSalaire.isExistFicheSalaire(numFicheSupp)) {
	                    gestionSalaire.supprimerFicheSalaire(numFicheSupp);
	                    System.out.println("Fiche de salaire supprimée avec succès!");
                    } else {
                        System.out.println("La fiche de salaire avec ce numéro n'existe pas.");
                    }
                    break;
                case 3:
                    System.out.print("Entrez le numéro de la fiche à modifier: ");
                    int numFicheMod = scanner.nextInt();
                    if (gestionSalaire.isExistFicheSalaire(numFicheMod)) {
                        System.out.print("Entrez le matricule de l'employé: ");
                        int matriculeEmployeeMod = scanner.nextInt();
                        if (!gestionEmployee.isExistEmployee(matriculeEmployeeMod)) {
                            System.out.println("L'employé avec ce matricule n'existe pas.");
                        } else {
                            System.out.print("Entrez le nouveau nombre d'heures: ");
                            int nbHeuresMod = scanner.nextInt();
                            System.out.print("Entrez le nouveau taux horaire: ");
                            float tauxHMod = scanner.nextFloat();
                            System.out.print("Entrez les nouvelles taxes: ");
                            float taxesMod = scanner.nextFloat();
                            gestionSalaire.modifierFicheSalaire(numFicheMod, nbHeuresMod, tauxHMod, taxesMod);
                            System.out.println("Fiche de salaire modifiée avec succès!");
                        }
                    } else {
                        System.out.println("La fiche de salaire avec ce numéro n'existe pas.");
                    }
                    break;
                case 4:
                    System.out.println("Liste de toutes les fiches de salaire:");
                    for (FicheSalaire ficheSalaire : gestionSalaire.getAllFiches()) {
                        gestionSalaire.afficherFicheSalaire(ficheSalaire.getNumFiche());
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }
}
