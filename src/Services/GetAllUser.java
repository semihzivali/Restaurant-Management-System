package Services;

import Models.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAllUser {
	
	// Tüm kullanıcıları listeleyen metot
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
            Logger lgr = Logger.getLogger(GetAllUser.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
