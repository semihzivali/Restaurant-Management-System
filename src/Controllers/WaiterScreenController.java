package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.OrderService;
import Models.Order;
import Utils.AlertHelper;
import application.SceneManager;

public class WaiterScreenController {

    @FXML
    private Button table1;
    @FXML
    private Button table2;
    @FXML
    private Button table3;
    @FXML
    private Button table4;
    @FXML
    private Button table5;
    @FXML
    private Button table6;
    @FXML
    private Button back_button;
    
  

    public void table1(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }

    public void table2(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }

    public void table3(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }

    public void table4(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }

    public void table5(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }
    
    public void table6(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterOrderScreen.fxml");
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterLoginScreen.fxml");
    }

}