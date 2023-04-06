package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.OpenView;

public class RegistrationFormController {

    public AnchorPane  CivilRPane;

    public void btnHomeOnAction(ActionEvent actionEvent) { OpenView.openView ("homeRegistrationForm",CivilRPane);}

    public void btnlandOnAction(ActionEvent actionEvent) {
        OpenView.openView ("landForm",CivilRPane);
    }

    public void btnIndivOnAction(ActionEvent actionEvent)  { OpenView.openView ("individualForm",CivilRPane);}

    public void btndeadOnAction(ActionEvent actionEvent) {
        OpenView.openView ("deadPeopleForm",CivilRPane);
    }

    public void btnMaternityOnAction(ActionEvent actionEvent) {
        OpenView.openView ("MaternityRegistForm",CivilRPane);
    }

    public void btnCandidateOnAction(ActionEvent actionEvent) {
        OpenView.openView ("candidateForm",CivilRPane);
    }

    public void btnDisableOnAction(ActionEvent actionEvent) { OpenView.openView ("disableRegistrationForm",CivilRPane); }

    public void btnDivisionOnAction(ActionEvent actionEvent) { OpenView.openView ("divisionRegistrationForm",CivilRPane);}


    @FXML
    void lblLogOnAction(MouseEvent event) {

    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",CivilRPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",CivilRPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {

    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {

    }

}
