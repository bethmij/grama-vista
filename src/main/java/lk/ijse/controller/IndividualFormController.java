package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.OpenView;

import java.io.IOException;

public class IndividualFormController {
    public AnchorPane indiroot1;

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",indiroot1);
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        OpenView.openView("individualForm2",indiroot1);
    }
}
