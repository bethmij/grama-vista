package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Division;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DivisionRegistrationFormController implements Initializable {
    public AnchorPane divisionPane;
    public TextField txtName;
    public TextField txtSecret;
    public TextField txtLand;
    public TextField txtAdmin;
    public Label lblDivision;
    public Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextOrderId();
    }

    private void generateNextOrderId() {
        try {
            String id = DivisionModel.getNextOrderId();
            lblDivision.setText(id);

            if ((!(DivisionManageFormController.division == null))){setDivController();}
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }


    }

    private void setDivController() {


        lblDivision.setText(DivisionManageFormController.division.getDivision_id());
        txtName.setText(DivisionManageFormController.division.getName());
        txtSecret.setText(DivisionManageFormController.division.getDiv_Secretariat());
        txtLand.setText(String.valueOf(DivisionManageFormController.division.getLand_area()));
        txtAdmin.setText(DivisionManageFormController.division.getAdmin_officer());
        btnSave.setText("Update");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals( "Save")) {
            if (!txtName.getText().equals("") && !txtAdmin.getText().equals("")) {
                try {
                    boolean isSaved = DivisionModel.save(new Division(
                            lblDivision.getText(),
                            txtName.getText(),
                            txtSecret.getText(),
                            txtAdmin.getText(),
                            Double.parseDouble(txtLand.getText())));

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully!").show();
                    } else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Fields * ").show();
        }else if (btnSave.getText().equals("Update") ) {

            if (!txtName.getText().equals("") && !txtAdmin.getText().equals("")) {
               try {
                    boolean isSaved = DivisionModel.update(new Division(
                            lblDivision.getText(),
                            txtName.getText(),
                            txtSecret.getText(),
                            txtAdmin.getText(),
                            Double.parseDouble(txtLand.getText())));

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
                    } else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Fields * ").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",divisionPane);
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtSecret.clear();
        txtLand.clear();
        txtAdmin.clear();
    }


}
