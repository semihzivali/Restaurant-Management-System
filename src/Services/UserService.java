package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.DataBaseConnection;

public class UserService {
	
	public boolean removeUser(String username, String password) {
        String query = "DELETE FROM \"Users\" WHERE username = ? AND password = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(UserService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
	
	
	
	 public List<String> getAllUsers() {
	        List<String> users = new ArrayList<>();
	        String query = "SELECT username FROM \"Users\"";

	        try (Connection con = DataBaseConnection.getConnection();
	             PreparedStatement pst = con.prepareStatement(query);
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                users.add(rs.getString("username"));
	            }
	            return users;

	        } catch (SQLException ex) {
	            Logger lgr = Logger.getLogger(UserService.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            return null;
	        }
	    }
	 
	 public boolean writeToDatabase(String userName, String userPassword, String userRole) {
	        String query = "INSERT INTO \"Users\"(username, password, role) VALUES(?, ?, ?)";

	        try (Connection con = DataBaseConnection.getConnection();
	             PreparedStatement pst = con.prepareStatement(query)) {

	            pst.setString(1, userName);
	            pst.setString(2, userPassword);
	            pst.setString(3, userRole);
	            pst.executeUpdate();
	            return true;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }
	 
	 
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
