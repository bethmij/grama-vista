package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class individualForm2Controller {
    public AnchorPane indiroot2;

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)indiroot2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/civilRegistrationForm.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)indiroot2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/individualForm.fxml"))));
        stage.setTitle("Individual Form");
        stage.centerOnScreen();

    }
}
