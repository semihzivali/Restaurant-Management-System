package Services;

import Models.Menu;
import Repository.MenuRepository;

import java.util.List;

public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService() {
        this.menuRepository = new MenuRepository();
    }

    public List<Menu> getAllMenuItems() {
        return menuRepository.getAllMenuItems();
    }

    public void addMenuItem(Menu menuItem) {
        if (menuItem.getPrice() > 0) {
            menuRepository.addMenuItem(menuItem);
        } else {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    public void updateMenuItem(Menu menuItem) {
        menuRepository.updateMenuItem(menuItem);
    }

    public void deleteMenuItem(int id) {
        menuRepository.deleteMenuItem(id);
    }
}