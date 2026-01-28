import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WizardCRUD {

    // 1. Connection Details (Constants are Pro practice)
    private static final String DB_URL = "jdbc:derby:wizard_db;create=true";
    private static final String TABLE_NAME = "wizards";

    public static void main(String[] args) {
        
        System.out.println("⚡ STARTING WIZARD CRUD OPERATIONS ⚡");

        // We use one Connection for the whole app here (Simulating a session)
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            
            System.out.println("✅ Connected to database.");

            // A. Clean Start (Optional: Drop table if exists so we start fresh)
            dropTable(conn);

            // B. CREATE (The Table)
            createTable(conn);

            // C. INSERT (Create Data)
            insertWizard(conn, 1, "Gandalf", "Fireworks", 99);
            insertWizard(conn, 2, "Harry", "Expelliarmus", 20);
            insertWizard(conn, 3, "Voldemort", "Avada Kedavra", 90);

            // D. READ (Select Data)
            System.out.println("\n--- 📜 Reading Initial List ---");
            readWizards(conn);

            // E. UPDATE (Change Data)
            System.out.println("\n--- 🆙 Updating Harry's Level ---");
            updateWizardLevel(conn, 2, 50); // Harry gets stronger
            readWizards(conn); // Verify change

            // F. DELETE (Remove Data)
            System.out.println("\n--- 🗑️ Deleting The Dark Lord ---");
            deleteWizard(conn, 3); // Bye bye Voldemort
            readWizards(conn); // Verify deletion

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n⚡ OPERATIONS COMPLETE ⚡");
    }

    // --- 🛠️ HELPER METHODS BELOW ---

    // 1. CREATE TABLE
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                   + "id INT PRIMARY KEY, "
                   + "name VARCHAR(50), "
                   + "spell VARCHAR(50), "
                   + "level INT)";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Table 'wizards' created.");
        }
    }

    // 2. INSERT (Create) - Using PreparedStatement
    private static void insertWizard(Connection conn, int id, String name, String spell, int level) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, spell, level) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, spell);
            pstmt.setInt(4, level);
            pstmt.executeUpdate();
            System.out.println("✨ Inserted: " + name);
        }
    }

    // 3. READ (Select)
    private static void readWizards(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.printf("%-5s %-15s %-20s %-5s%n", "ID", "NAME", "SPELL", "LVL");
            System.out.println("-----------------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String spell = rs.getString("spell");
                int level = rs.getInt("level");
                
                System.out.printf("%-5d %-15s %-20s %-5d%n", id, name, spell, level);
            }
        }
    }

    // 4. UPDATE
    private static void updateWizardLevel(Connection conn, int id, int newLevel) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET level = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newLevel);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Updated Wizard ID " + id + " to Level " + newLevel);
            } else {
                System.out.println("⚠️ Wizard not found!");
            }
        }
    }

    // 5. DELETE
    private static void deleteWizard(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("❌ Deleted Wizard ID " + id);
            }
        }
    }

    // 6. UTILITY: Drop Table (So we can run this code multiple times without errors)
    private static void dropTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE " + TABLE_NAME);
            System.out.println("🧹 Cleaned up old table.");
        } catch (SQLException e) {
            // Ignore error if table doesn't exist yet (First run)
            if (!e.getSQLState().equals("42Y55")) {
                e.printStackTrace();
            }
        }
    }
}