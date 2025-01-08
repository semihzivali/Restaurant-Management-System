module RestaurantManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controllers to javafx.fxml;
	opens Models to javafx.base;
}
