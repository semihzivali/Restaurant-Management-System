package Repository.Abstract;

import java.util.List;

import Models.Menu;

public interface IMenuRepository {
	
	public List<Menu> getAllMenuItems();
	
	public void addMenuItem(Menu menuItem);
	
	public void updateMenuItem(Menu menuItem);
	
	 public void deleteMenuItem(int id);

}
