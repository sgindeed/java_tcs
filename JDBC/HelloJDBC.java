import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloJDBC {

    public static void main(String[] args) {
        
        // 1. The Connection URL
        // "jdbc:derby:" is the protocol. "testdb" is the DB name. ";create=true" makes it if needed.
        String url = "jdbc:derby:testdb;create=true";
        
        System.out.println("🚀 Starting Connection check...");

        // 2. Try-with-resources (We will cover this in detail later, but it's best practice)
        // This automatically closes the connection when done.
        try (Connection conn = DriverManager.getConnection(url)) {
            
            if (conn != null) {
                System.out.println("✅ BOOM! Connected to the database successfully!");
                System.out.println("Driver Name: " + conn.getMetaData().getDriverName());
                System.out.println("Database Product: " + conn.getMetaData().getDatabaseProductName());
            }

        } catch (SQLException e) {
            // If something goes wrong (like a bad URL), this runs.
            System.err.println("❌ Connection Failed!");
            e.printStackTrace();
        }
    }
}