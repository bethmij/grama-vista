package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.util.OpenView;

import java.io.File;
import java.io.IOException;
import java.util.EventObject;

public class HomeRegistrationFormController {
    public AnchorPane  HomePane;

    public void btnSaveOnAction(ActionEvent actionEvent) {

        /*Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();*/
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",HomePane);
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
    }
}
