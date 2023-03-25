package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LandFormController {
    public AnchorPane landRoot;

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnOwnerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ownershipForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Ownership Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)landRoot.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/civilRegistrationForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Civil Registration Form");
        stage.centerOnScreen();
    }

    private void openView (String view ){

    }


}
