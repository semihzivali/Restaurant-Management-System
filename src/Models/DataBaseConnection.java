package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static final String URL = "jdbc:postgresql://localhost:5432/your_project_name";
	private static final String USER = "your_username";
	private static final String PASSWORD = "your_password";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Database connection successful!");
		} catch (SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
		}
		return connection;
	}
	
}
