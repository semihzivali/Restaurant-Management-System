package Controllers;

import Services.ReadUser;
import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ManagerScreenController {
	
	@FXML
    private Button add_employee_button;
	@FXML
    private Button remove_employee_button;
	@FXML
    private Button manage_menu_button;
	@FXML
    private Button show_tables;
	@FXML
    private Button manager_logout_button;
	
  
    private ReadUser userService = new ReadUser();
  
    
    public void addEmployee(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/AddEmployee.fxml");
    }
    public void removeEmployee(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/RemoveEmployee.fxml");
    }
    public void manageMenu(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManageMenuScreen.fxml");
    }
    public void showTables(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerShowTables.fxml");
    }
    public void managerLogout(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml");
    }
   
   

}