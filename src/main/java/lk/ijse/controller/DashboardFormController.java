package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.OpenView;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane dashboardpane;

    public void btnCivilRegOnAction(ActionEvent actionEvent) throws IOException {
        OpenView.openView("registrationForm",dashboardpane);
    }

    public void btnManageOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",dashboardpane);
    }
}
