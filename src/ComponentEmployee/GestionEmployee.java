package ComponentEmployee;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionEmployee {
	private Connection connection;

    public GestionEmployee() {
        try {
            // Connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionemployee", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterEmployee(Employee e) {
        String sql = "INSERT INTO Employee (matricule, nom, prenom, adresse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, e.getMatricule());
            stmt.setString(2, e.getNom());
            stmt.setString(3, e.getPrenom());
            stmt.setString(4, e.getAdresse());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ajouterEmployee(int m, String nom, String prenom, String adresse) {
        String sql = "INSERT INTO Employee (matricule, nom, prenom, adresse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, m);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setString(4, adresse);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void supprimerEmployee(int m) {
        String sql = "DELETE FROM Employee WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, m);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifierEmployee(int m, String nom, String prenom, String adresse) {
        String sql = "UPDATE Employee SET nom = ?, prenom = ?, adresse = ? WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setInt(4, m);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isExistEmployee(int m) {
        String sql = "SELECT 1 FROM Employee WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, m);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Employee chercherEmployee(int m) {
        String sql = "SELECT * FROM Employee WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, m);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("adresse")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void AfficherEmployees() {
        String sql = "SELECT * FROM Employee";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("La liste des employés:");
            while (rs.next()) {
                System.out.println("Matricule: " + rs.getInt("matricule"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Prenom: " + rs.getString("prenom"));
                System.out.println("Adresse: " + rs.getString("adresse"));
                System.out.println("------------------------");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
	

