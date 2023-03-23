package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class individualFormController {
    public AnchorPane indiroot1;


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)indiroot1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CivilRegistrationForm.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)indiroot1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/individualForm2.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }
}
