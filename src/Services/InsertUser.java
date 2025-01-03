package Services;

import Models.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertUser {

    // Kullanıcıyı veritabanına ekleyen metot
    public void writeToDatabase(String userName, String userPassword, String userRole) {
        String query = "INSERT INTO \"Users\"(username, password, role) VALUES(?, ?, ?)";
    //    String role = "manager"; // Burada role sabit olabilir ya da parametre olarak alınabilir.

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.setString(3, userRole);
            pst.executeUpdate();
            System.out.println("Successfully created.");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(UserService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}