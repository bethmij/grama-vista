package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Dead;
import lk.ijse.dto.Detail;
import lk.ijse.dto.Disable;
import lk.ijse.model.*;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;
import static lk.ijse.controller.DeadManageFormController.dead;
import static lk.ijse.controller.DisableManageFormController.disable;

public class DisableRegistrationFormController implements Initializable {
    public AnchorPane disablePane;
    public Label lblId;
    public TextField txtDisability;
    public Label lblName;
    public TextField txtDescription;
    public ComboBox cmbCivil;
    public Button btn1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        generateNextId();
        if ((!(disable == null))) {
            setDisableController();
        }
    }

    private void setDisableController() {
        lblId.setText(disable.getID());
        txtDisability.setText(disable.getDisability());
        lblName.setText(disable.getName());
        if(disable.getDescription()!=null)
            txtDescription.setText(disable.getDescription());
        cmbCivil.setValue(disable.getCivil_ID());
        btn1.setText("Update");
    }


    private void loadCivilId() {
        List<String> id = null;
        try {
            id = CivilModel.loadCivilId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add("C00"+ids);
        }
        cmbCivil.setItems(dataList);
    }

    private void generateNextId() {
        try {
            lblId.setText("DS00"+DisableModel.getNextId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if(btn1.getText().equals("Save")) {
            if (!(cmbCivil.getValue() == null) && !txtDisability.getText().equals("")) {
                String[] id = lblId.getText().split("DS00");
                String[] civil_id = String.valueOf(cmbCivil.getValue()).split("C00");

                try {
                    boolean isSaved = DisableModel.save(new Disable(
                            id[1], civil_id[1], lblName.getText(), txtDisability.getText(), txtDescription.getText()));

                    if (isSaved) {
                        Detail detail = new Detail("Registration", "bethmi", LocalTime.now(), LocalDate.now(), "Registering disable_people id - " + lblId.getText() + " \nname - " + lblName.getText());
                        try {
                            DetailModel.save(detail);
                        } catch (SQLException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                    }else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtDisability.setStyle("-fx-border-color:  #ef0d20; ");
                cmbCivil.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }else if(btn1.getText().equals("Update")) {
            if (!(cmbCivil.getValue() == null) && !txtDisability.getText().equals("")) {
                try {
                    boolean isUpdate = DisableModel.update(new Disable(lblId.getText(), (String) cmbCivil.getValue(), lblName.getText(), txtDisability.getText(), txtDescription.getText()));
                    if (isUpdate)
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtDisability.setStyle("-fx-border-color:  #ef0d20; ");
                cmbCivil.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",disablePane);
    }


    public void cmbCivilOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCivil.getValue();
        String[] strings = id.split("C00");

        try {
            lblName.setText(CivilModel.searchById(strings[1]));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtDisability.clear();
        lblName.setText("");
        txtDescription.clear();
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            Detail detail = new Detail("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                boolean isSaved = DetailModel.save(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",disablePane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",disablePane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",disablePane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",disablePane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",disablePane);
    }
}
