package ComponentSalaire;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GestionSalaire {
	
	private Connection connection;

    public GestionSalaire() {
        try {
            // Connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionemployee", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterFicheSalaire(FicheSalaire fiche) {
        String sql = "INSERT INTO FicheSalaire (numFiche, id_Employee, dateF, nbHeures, tauxH, montantBrut, taxes, montantNet) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fiche.getNumFiche());
            stmt.setInt(2, fiche.getMatriculeEmployee());
            stmt.setDate(3, Date.valueOf(fiche.getDateF()));
            stmt.setDouble(4, fiche.getNbHeures());
            stmt.setDouble(5, fiche.getTauxH());
            stmt.setDouble(6, fiche.getMontantBrut());
            stmt.setDouble(7, fiche.getTaxes());
            stmt.setDouble(8, fiche.getMontantNet());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void supprimerFicheSalaire(int numFiche) {
        String sql = "DELETE FROM FicheSalaire WHERE numFiche = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numFiche);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifierFicheSalaire(int numFiche, int nbHeures, float tauxH, float taxes) {
        // Calcul des montants
        float montantBrut = nbHeures * tauxH;
        float montantNet = montantBrut - (montantBrut * taxes);

        // SQL de mise à jour
        String sql = "UPDATE FicheSalaire SET nbHeures = ?, tauxH = ?, montantBrut = ?, taxes = ?, montantNet = ? WHERE numFiche = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Paramètres pour la mise à jour
            stmt.setInt(1, nbHeures);
            stmt.setFloat(2, tauxH);
            stmt.setFloat(3, montantBrut);
            stmt.setFloat(4, taxes);
            stmt.setFloat(5, montantNet);
            stmt.setInt(6, numFiche);

            // Exécution de la mise à jour
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void afficherFicheSalaire(int numFiche) {
        String sql = "SELECT * FROM FicheSalaire WHERE numFiche = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numFiche);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Informations de la fiche :");
                System.out.println("Numéro de fiche: " + rs.getInt("numFiche"));
                System.out.println("Matricule: " + rs.getInt("id_Employee"));
                System.out.println("Date de fiche: " + rs.getDate("dateF"));
                System.out.println("Nombre d'heures: " + rs.getDouble("nbHeures"));
                System.out.println("Taux horaire: " + rs.getDouble("tauxH"));
                System.out.println("Montant brut: " + rs.getDouble("montantBrut"));
                System.out.println("Taxes: " + rs.getDouble("taxes"));
                System.out.println("Montant net: " + rs.getDouble("montantNet"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<FicheSalaire> getAllFiches() {
        List<FicheSalaire> fiches = new ArrayList<>();
        String sql = "SELECT * FROM FicheSalaire";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int matriculeEmployee = rs.getInt("id_Employee");
                int numFiche = rs.getInt("numFiche");
                LocalDate dateF = rs.getDate("dateF").toLocalDate();
                int nbHeures = rs.getInt("nbHeures");
                float tauxH = rs.getFloat("tauxH");
                float taxes = rs.getFloat("taxes");

                // Créer l'objet FicheSalaire en utilisant le constructeur approprié
                FicheSalaire fiche = new FicheSalaire(matriculeEmployee, numFiche, dateF, nbHeures, tauxH, taxes);

                fiches.add(fiche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fiches;
    }
    
    public boolean isExistFicheSalaire(int numFiche) {
        String sql = "SELECT 1 FROM FicheSalaire WHERE numFiche = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numFiche);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


}