package Services.Concrete;

import Models.Menu;
import Repository.Abstract.IMenuRepository;
import Services.Abstract.IMenuService;

import java.util.List;

public class MenuService implements IMenuService {
	
	private final IMenuRepository menuRepository;
	
    public MenuService(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenuItems() {
        return menuRepository.getAllMenuItems();
    }

    @Override
    public void addMenuItem(Menu menuItem) {
        if (menuItem.getPrice() > 0) {
            menuRepository.addMenuItem(menuItem);
        } else {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    @Override
    public void updateMenuItem(Menu menuItem) {
        menuRepository.updateMenuItem(menuItem);
    }

    @Override
    public void deleteMenuItem(int id) {
    	if ( id > 0) {
    		menuRepository.deleteMenuItem(id);
        } else {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
    }
    
}
