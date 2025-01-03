package Services;

import Models.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUser {
	
	// Kullanıcı doğrulama işlemi
    public boolean checkUser(String username, String password) {
        String query = "SELECT * FROM \"Users\" WHERE username = ? AND password = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();  // Kullanıcı doğruysa true döner
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
