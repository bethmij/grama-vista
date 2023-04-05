package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class ManageFormController {
    public AnchorPane ManagePane;

    public void btnHomeOnAction(ActionEvent actionEvent) {
        OpenView.openView("homeManageForm",ManagePane);
    }

    public void btnIndivOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilManageForm",ManagePane);
    }

    public void btnlandOnAction(ActionEvent actionEvent) {
        OpenView.openView("landManageForm",ManagePane);
    }

    public void btndeadOnAction(ActionEvent actionEvent) {
        OpenView.openView("deadManageForm",ManagePane);
    }

    public void btnMaternityOnAction(ActionEvent actionEvent) {
        OpenView.openView("maternityManageForm",ManagePane );
    }

    public void btnCandidateOnAction(ActionEvent actionEvent) {
        OpenView.openView("candidateManageForm",ManagePane);
    }

    public void btnDisableOnAction(ActionEvent actionEvent) {
        OpenView.openView("disableManageForm",ManagePane);
    }

    public void btnDivisionOnAction(ActionEvent actionEvent) {
        OpenView.openView("divisionManageForm",ManagePane);
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
        OpenView.openView("userManageForm",ManagePane);
    }
}
