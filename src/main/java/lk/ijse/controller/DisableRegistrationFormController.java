package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class DisableRegistrationFormController {
    public AnchorPane disablePane;

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",disablePane);
    }
}
