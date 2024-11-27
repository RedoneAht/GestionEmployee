package ComponentSalaire;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interfaces.GestionSalaireInterface;

public class GestionSalaire implements GestionSalaireInterface {
	
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

    public void modifierFicheSalaire(int numFiche, double nbHeures, double tauxH, double montantBrut, double taxes, double montantNet) {
        String sql = "UPDATE FicheSalaire SET nbHeures = ?, tauxH = ?, montantBrut = ?, taxes = ?, montantNet = ? WHERE numFiche = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, nbHeures);
            stmt.setDouble(2, tauxH);
            stmt.setDouble(3, montantBrut);
            stmt.setDouble(4, taxes);
            stmt.setDouble(5, montantNet);
            stmt.setInt(6, numFiche);
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
}
