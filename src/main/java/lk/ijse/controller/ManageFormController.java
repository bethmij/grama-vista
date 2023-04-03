package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class ManageFormController {
    public AnchorPane ManagePane;

    public void btnHomeOnAction(ActionEvent actionEvent) {
    }

    public void btnIndivOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilManageForm",ManagePane);
    }

    public void btnlandOnAction(ActionEvent actionEvent) {
    }

    public void btndeadOnAction(ActionEvent actionEvent) {
    }

    public void btnMaternityOnAction(ActionEvent actionEvent) {
    }

    public void btnCandidateOnAction(ActionEvent actionEvent) {
        OpenView.openView("candidateManageForm",ManagePane);
    }

    public void btnDisableOnAction(ActionEvent actionEvent) {
    }

    public void btnDivisionOnAction(ActionEvent actionEvent) {
        OpenView.openView("divisionManageForm",ManagePane);
    }
}
