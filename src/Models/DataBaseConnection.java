package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {

	 private static final String URL = "jdbc:postgresql://localhost:5432/JavaProject";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "semih0902";

	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("PostgreSQL bağlantısı başarılı!");
	        } catch (SQLException e) {
	            System.out.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
	        }
	        return connection;
	    }
}
