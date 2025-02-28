package Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties")); 
            
            URL = properties.getProperty("DB_URL");
            USER = properties.getProperty("DB_USER");
            PASSWORD = properties.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            System.out.println("Failed to load database configuration: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }
}
