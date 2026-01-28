import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {
        
        // We use the same URL. Derby will just open the existing "testdb" folder.
        String url = "jdbc:derby:testdb"; 
        
        // SQL to create a table named 'wizards'
        // ID (Number), Name (Text), Level (Number)
        String sql = "CREATE TABLE wizards ("
                   + "id INT PRIMARY KEY, "
                   + "name VARCHAR(50), "
                   + "level INT)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) { // Initialize Statement here
            
            // Execute the SQL
            stmt.executeUpdate(sql);
            System.out.println("✅ Table 'wizards' created successfully!");

        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("⚠️ Table already exists. Moving on!");
            } else {
                e.printStackTrace();
            }
        }
    }
}