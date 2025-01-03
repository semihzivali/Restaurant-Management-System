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
    private Button waiter_add_button;
	@FXML
    private Button waiter_remove_button;
	@FXML
    private Button kitchen_add_button;
	@FXML
    private Button kitchen_remove_button;
	@FXML
    private Button show_tables;
	@FXML
    private Button manager_logout_button;
	
  
    private ReadUser userService = new ReadUser();
  
    
    public void addWaiter(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/AddWaiterScreen.fxml");
    }
    public void removeWaiter(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/RemoveWaiterScreen.fxml");
    }
    public void addKitchen(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/AddKitchenScreen.fxml");
    }
    public void removeKitchen(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/RemoveKitchenScreen.fxml");
    }
    public void showTables(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerShowTables.fxml");
    }
    public void managerLogout(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml");
    }
   
   

}