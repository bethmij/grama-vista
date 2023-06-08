package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.ManageBO;
import lk.ijse.bo.custom.impl.ManageBOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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

    ManageBO manageBO =new ManageBOImpl();

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                manageBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",ManagePane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",ManagePane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",ManagePane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",ManagePane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",ManagePane);
    }
}
