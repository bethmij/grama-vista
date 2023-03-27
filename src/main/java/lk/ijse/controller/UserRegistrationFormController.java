package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.OpenView;

import java.io.IOException;

public class UserRegistrationFormController {
    public AnchorPane userPane;

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",userPane);
    }


}
