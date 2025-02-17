package Repository.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.DataBaseConnection;
import Repository.Abstract.IUserRepository;

public class UserRepository implements IUserRepository {

    public boolean removeUser(String username, String password) {
        String query = "DELETE FROM \"Users\" WHERE username = ? AND password = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
            return null;
        }
    }

    public boolean addUser(String userName, String userPassword, String userRole) {
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
        String query = "SELECT id FROM \"Users\" WHERE username = ? AND password = ? AND role = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // User not found.
    }
}
