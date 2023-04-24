package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Candidate;
import lk.ijse.dto.Dead;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DeadModel;
import lk.ijse.model.MaternityModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.DeadManageFormController.dead;

public class DeadPeopleFormController implements Initializable {
    public AnchorPane deadPane;
    public ComboBox cmbCivil;
    public DatePicker dtpDate;
    public Label lblName;
    public Label lblID;
    public Button btn1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        generateNextId();
        if ((!(dead == null))) {
            setDeadController();
        }
    }

    private void setDeadController() {
        cmbCivil.setValue(dead.getCivil_ID());
        dtpDate.setValue(dead.getDate());
        lblName.setText(dead.getName());
        lblID.setText(dead.getReg_id());
        btn1.setText("Update");
    }

    private void generateNextId() {
        try {
            lblID.setText("DD00"+ DeadModel.getNextId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",deadPane);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        if(btn1.getText().equals("Save")) {
            String id = (String) cmbCivil.getValue();
            String[] civil_id = id.split("C00");
            String[] reg_id = lblID.getText().split("DD00");
            String division_id = CivilModel.getDivisionId(Integer.valueOf(civil_id[1]));

            try {
                boolean isSaved = DeadModel.save(new Dead(reg_id[1], civil_id[1], lblName.getText(), dtpDate.getValue()), division_id);

                if (isSaved)
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else if(btn1.getText().equals("Update")){
            try {
                //boolean isUpdate = DeadModel.update(new Dead(reg_id[1], civil_id[1], lblName.getText(), dtpDate.getValue()));
                boolean isUpdate = DeadModel.update(new Dead(lblID.getText(), (String) cmbCivil.getValue(), lblName.getText(), dtpDate.getValue()));
                if (isUpdate)
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
         dtpDate.setValue(null);
         lblName.setText("");
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",deadPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",deadPane);
    }


    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",deadPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",deadPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",deadPane);
    }
}
