package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import Utils.SceneManager;

public class FirstScreenController {

    @FXML
    private Button managerButton;
    @FXML
    private Button waiterButton;
    

    public void ManagerLoginController(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerLoginScreen.fxml");
    }

    public void WaiterLoginController(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterLoginScreen.fxml");
    }

  
}
