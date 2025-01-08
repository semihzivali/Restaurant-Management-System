package Services;

import Models.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUser {
	
	 public int getUserId(String username, String password, String role) {
	        try (Connection connection = DataBaseConnection.getConnection()) {
	            String query = "SELECT id FROM public.\"Users\" WHERE username = ? AND password = ? AND role = ?";
	            PreparedStatement stmt = connection.prepareStatement(query);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            stmt.setString(3, role);
	            
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	        } catch (Exception e) {
	            System.out.println("Error fetching user ID: " + e.getMessage());
	        }
	        return -1; // Kullanıcı bulunamadığında
	    }

}
