package JDBC;

import java.sql.*;
import java.util.*;

public class ServerConnection {

    static String url = "jdbc:derby:studentDB;create=true"; // Derby specific url
    //static String user = "system";                       --- Not needed for derby
    //static String password = "Passw0rd!";                --- Not needed for derby

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected");

            insertStudent(connection);
            readStudent(connection);
            updateStudent(connection);
            deleteStudent(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("Derby shut down normally");
            }
        }
    }

    //CREATE
    public static void insertStudent(Connection connection) throws SQLException {

        String sql = "INSERT INTO student VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, 102);
        ps.setString(2, "Alex");
        ps.setInt(3, 88);

        ps.executeUpdate();
        System.out.println("Insert successful");

        ps.close();
    }

    //READ
    public static void readStudent(Connection connection) throws SQLException {

        String sql = "SELECT * FROM student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("Student Table:");
        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getInt("marks")
            );
        }

        rs.close();
        ps.close();
    }

    //UPDATE
    public static void updateStudent(Connection connection) throws SQLException {

        String sql = "UPDATE student SET marks = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, 95);
        ps.setInt(2, 102);

        ps.executeUpdate();
        System.out.println("Update successful");

        ps.close();
    }

    //DELETE
    public static void deleteStudent(Connection connection) throws SQLException {

        String sql = "DELETE FROM student WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, 102);

        ps.executeUpdate();
        System.out.println("Delete successful");

        ps.close();
    }
}
