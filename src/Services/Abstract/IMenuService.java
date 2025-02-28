package Services.Abstract;

import java.util.List;

import Models.Menu;

public interface IMenuService {
	
	public List<Menu> getAllMenuItems();
	
	public Menu getMenuItemById(int id);
	
	public void addMenuItem(Menu menuItem);
	
	public void updateMenuItem(Menu menuItem);
	
	public void deleteMenuItem(int id);

}
