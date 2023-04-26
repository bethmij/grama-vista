package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Detail;
import lk.ijse.dto.Division;
import lk.ijse.model.DetailModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class DivisionRegistrationFormController implements Initializable {
    public AnchorPane divisionPane;
    public TextField txtName;
    public TextField txtSecret;
    public TextField txtLand;
    public TextField txtAdmin;
    public Label lblDivision;
    public Button btnSave;
    public Label lblName;
    public Label lblSecretary;
    public Label lblAdmin;
    public Label lblArea;

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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
            if ( !txtSecret.getText().equals("") && !txtSecret.getText().equals("") ) {
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
            }else{
                txtName.setStyle("-fx-border-color:  #ef0d20; ");
                txtSecret.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }else if (btnSave.getText().equals("Update") ) {

            if ( !txtSecret.getText().equals("") && !txtSecret.getText().equals("") ) {
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
            } else{
                txtName.setStyle("-fx-border-color:  #ef0d20; ");
                txtSecret.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
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

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            Detail detail = new Detail("Logged out", "bethmi",LocalTime.now(), LocalDate.now(),"");
            try {
                boolean isSaved = DetailModel.save(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",divisionPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",divisionPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",divisionPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",divisionPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",divisionPane);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        if (!txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblName.setText("This filed can not contain numeric values!");
        }
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        if (txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblName.setText("");
        }
    }

    public void txtSecOnKeyReleased(KeyEvent keyEvent) {
        if (!txtSecret.getText().matches("^[A-Za-z\\s]*$")) {
            txtSecret.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblSecretary.setText("This filed can not contain numeric values!");
        }
    }

    public void txtSecOnKeyTyped(KeyEvent keyEvent) {
        if (txtSecret.getText().matches("^[A-Za-z\\s]*$")) {
            txtSecret.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblSecretary.setText("");
        }
    }

    public void txtAreaOnKeyReleased(KeyEvent keyEvent) {
        if (!txtLand.getText().matches("^[0-9.]*$")) {
            txtLand.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblArea.setText("This filed can only contain numeric values!");
        }
    }

    public void txtAreaOnKeyTyped(KeyEvent keyEvent) {
        if (txtLand.getText().matches("^[0-9.]*$")) {
            txtLand.setStyle("-fx-border-color: null; -fx-font-size: 16px;");
            lblArea.setText("");
        }
    }

    public void txtAdminOnKeyReleased(KeyEvent keyEvent) {
        if (!txtAdmin.getText().matches("^[A-Za-z\\s]*$")) {
            txtAdmin.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblAdmin.setText("This filed can not contain numeric values!");
        }
    }

    public void txtAdminOnKeyTyped(KeyEvent keyEvent) {
        if (txtAdmin.getText().matches("^[A-Za-z\\s]*$")) {
            txtAdmin.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblAdmin.setText("");
        }
    }
}
