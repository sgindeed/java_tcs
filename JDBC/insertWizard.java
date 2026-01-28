import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Notice the new import
import java.sql.SQLException;

public class InsertWizard {

    public static void main(String[] args) {
        String url = "jdbc:derby:testdb";
        
        // 1. The SQL Template (Notice the question marks!)
        String sql = "INSERT INTO wizards (id, name, level) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // 2. Insert First Wizard
            pstmt.setInt(1, 1);          // First ? matches ID
            pstmt.setString(2, "Gandalf"); // Second ? matches Name
            pstmt.setInt(3, 99);         // Third ? matches Level
            pstmt.executeUpdate();       // Send it!
            
            // 3. Insert Second Wizard (Reuse the same PreparedStatement!)
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Harry");
            pstmt.setInt(3, 15);
            pstmt.executeUpdate();
            
            System.out.println("✅ Wizards inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}