package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane dashboardpane;
    public void btnCivilRegOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)dashboardpane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CivilRegistrationForm.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }
}
