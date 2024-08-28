package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    // Establish a connection to the SQLite database
    public Connection connect() {
        Connection connection = null;
        try {
            // SQLite connection string
            String url = "jdbc:sqlite:C:/Users/deanm/CTAC_july_2024/BookStore.db"; // Replace with the correct path to your SQLite DB file
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Execute a SELECT query to retrieve all books
    public void selectAllBooks() {
        String sql = "SELECT * FROM books";  // Adjust table name if necessary
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            // Print out the results
            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the application
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }
}
