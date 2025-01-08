package Repository;

import Models.DataBaseConnection;
import Models.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

	public List<Menu> getAllMenuItems() {
	    List<Menu> menuItems = new ArrayList<>();
	    try (Connection conn = DataBaseConnection.getConnection()) {
	        String query = "SELECT * FROM \"Menu\"";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Menu menu = new Menu(
	                    rs.getString("item_name"),
	                    rs.getDouble("price"),
	                    rs.getInt("category_id"),
	                    rs.getInt("stock_quantity")
	            );
	            menu.setId(rs.getInt("id"));
	            menuItems.add(menu);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return menuItems;
	}

    public void addMenuItem(Menu menuItem) {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "INSERT INTO \"Menu\" (item_name, price, category_id, stock_quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, menuItem.getItemName());
            stmt.setDouble(2, menuItem.getPrice());
            stmt.setInt(3, menuItem.getCategoryId());
            stmt.setInt(4, menuItem.getStockQuantity());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        menuItem.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(Menu menuItem) {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "UPDATE \"Menu\" SET item_name = ?, price = ?, category_id = ?, stock_quantity = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, menuItem.getItemName());
            stmt.setDouble(2, menuItem.getPrice());
            stmt.setInt(3, menuItem.getCategoryId());
            stmt.setInt(4, menuItem.getStockQuantity());
            stmt.setInt(5, menuItem.getId()); 
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "DELETE FROM \"Menu\" WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
