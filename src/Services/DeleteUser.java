package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.DataBaseConnection;

public class DeleteUser {
	
	 public boolean removeUser(String username, String password) {
	        String query = "DELETE FROM \"Users\" WHERE username = ? AND password = ?";

	        try (Connection con = DataBaseConnection.getConnection();
	             PreparedStatement pst = con.prepareStatement(query)) {

	            pst.setString(1, username);
	            pst.setString(2, password);

	            int affectedRows = pst.executeUpdate();
	            return affectedRows > 0;

	        } catch (SQLException ex) {
	            Logger lgr = Logger.getLogger(DeleteUser.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            return false;
	        }
	    }

}
