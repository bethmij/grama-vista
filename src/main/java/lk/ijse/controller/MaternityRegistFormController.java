package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class MaternityRegistFormController {
    public AnchorPane maternityPane;

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",maternityPane);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
    }
}
