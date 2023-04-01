package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextOrderId();
    }

    private void generateNextOrderId() {
        try {
            String id = DivisionModel.getNextOrderId();
            lblDivision.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
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
                    clear();
                    generateNextOrderId();
                } else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Fields * ").show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",divisionPane);
    }

    private void clear (){
        txtName.clear();
        txtSecret.clear();
        txtAdmin.clear();
        txtLand.clear();
    }


    public void btnResetOnAction(ActionEvent actionEvent) {
    }
}
