package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	public static void writeToDatabase(String userName, String userPassword) {

        String url = "jdbc:postgresql://localhost:5432/JavaProject";
        String user = "postgres";
        String password = "semih0902";

        String name = userName;
        String pass = userPassword;
        String role = "manager";

        // query
        String query = "INSERT INTO \"Users\"(username, password, role) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, pass);
            pst.setString(3, role);
            pst.executeUpdate();
            System.out.println("Sucessfully created.");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DataBaseConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}
