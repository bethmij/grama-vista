package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Dead;
import lk.ijse.dto.Disable;
import lk.ijse.dto.Maternity;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DeadModel;
import lk.ijse.model.DisableModel;
import lk.ijse.model.MaternityModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.DeadManageFormController.dead;
import static lk.ijse.controller.MaternityManageFormController.maternity;

public class MaternityRegistFormController implements Initializable {
    public AnchorPane maternityPane;
    public ComboBox cmbCivil;
    public TextField txtMidWife;
    public Label lblID;
    public Label lblName;
    public TextField txtMonths;
    public DatePicker dtpDate;
    public Button btnSave;
    public TextField txtMonth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        generateNextId();
        if ((!(maternity == null))) {
            setMaternityController();
        }
    }

    private void setMaternityController() {
        cmbCivil.setValue("C00"+maternity.getCivil_ID());
        txtMidWife.setText(maternity.getMid_wife());
        lblID.setText("M00"+maternity.getID());
        lblName.setText(maternity.getName());
        btnSave.setText("Update");
    }

    private void loadCivilId() {
        List<String> id = null;
        try {
            id = CivilModel.loadCivilId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add("C00"+ids);
        }
        cmbCivil.setItems(dataList);

    }

    private void generateNextId() {
        try {
            lblID.setText("M00"+ MaternityModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",maternityPane);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save")) {
            String[] id = lblID.getText().split("M00");
            String[] civil_id = String.valueOf(cmbCivil.getValue()).split("C00");

            try {
                boolean isSaved = MaternityModel.save(new Maternity(id[1], civil_id[1], lblName.getText(),Integer.valueOf(txtMonths.getText()), txtMidWife.getText()));

                if (isSaved)
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else if(btnSave.getText().equals("Update")){
            String[] id = lblID.getText().split("M00");
            String[] civil_id = String.valueOf(cmbCivil.getValue()).split("C00");

            try {
                boolean isUpdated = MaternityModel.update(new Maternity(id[1], civil_id[1], lblName.getText(), Integer.valueOf(txtMonths.getText()),txtMidWife.getText()));

                if (isUpdated)
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
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
        txtMidWife.clear();
        lblName.setText("");
        txtMonths.clear();
        dtpDate.setValue(null);
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",maternityPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",maternityPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",maternityPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",maternityPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {

    }
}
