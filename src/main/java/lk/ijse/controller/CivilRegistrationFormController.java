package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CivilRegistrationFormController {
    public AnchorPane  CivilRPane;
    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)CivilRPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/homeRegistrationForm.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }

    public void btnCivilOnAction(MouseEvent mouseEvent) {
    }
}
