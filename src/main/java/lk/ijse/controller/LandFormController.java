package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.OpenView;

import java.io.IOException;

public class LandFormController {
    public AnchorPane landRoot;

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnOwnerOnAction(ActionEvent actionEvent){
        OpenView.openView("ownershipForm",landRoot);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",landRoot);
    }

    private void openView (String view ){

    }


}
