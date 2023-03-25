package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class IndividualFormController {
    public AnchorPane indiroot1;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)indiroot1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CivilRegistrationForm.fxml"))));
        stage.setTitle("Civil Registration");
        stage.centerOnScreen();
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)indiroot1.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/individualForm2.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Individual Form");
        stage.centerOnScreen();
    }
}
