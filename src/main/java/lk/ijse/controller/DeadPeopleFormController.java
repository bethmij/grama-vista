package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class DeadPeopleFormController {
    public AnchorPane deadPane;

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",deadPane);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }
}
