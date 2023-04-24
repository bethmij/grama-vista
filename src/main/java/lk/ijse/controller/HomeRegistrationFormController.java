package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dto.Candidate;
import lk.ijse.dto.Dead;
import lk.ijse.dto.Residence;
import lk.ijse.model.*;
import lk.ijse.util.OpenView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.DisableManageFormController.disable;
import static lk.ijse.controller.HomeManageFormController.residence;

public class HomeRegistrationFormController implements Initializable {
    public AnchorPane  HomePane;
    public TextField txtName;
    public TextField txtCount;
    public TextField txtChildCount;
    public ChoiceBox cbType;
    public TextField txtHomeID;
    public CheckBox ckbElectricity;
    public CheckBox ckbWater;
    public ChoiceBox cbDivision;
    public Label lblName;
    public TextField txtAddress1;
    public Button Save;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        loadResiType();
        if ((!(residence == null))) {
            setDisableController();
        }
    }

    private void setDisableController() {
        txtName.setText(residence.getHouse_holder_name());
        txtCount.setText(String.valueOf(residence.getMember_count()));
        txtChildCount.setText(String.valueOf(residence.getCount_below_18()));
        cbType.setValue(residence.getResidence_type());
        txtHomeID.setText(residence.getHome_id());
        ckbElectricity.setSelected(residence.getElectricity().equals("Yes") ? true:false );
        ckbWater.setSelected(residence.getWater_supply().equals("Yes") ? true:false);
        cbDivision.setValue(residence.getDivision_id());
        txtAddress1.setText(residence.getAddress());
        Save.setText("Update");
    }


    private void loadResiType() {
        String[] type = new String[]{"Full built","Half built","Temporary"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbType.setItems(dataList);
    }

    private void loadDivisionID() {
        try {
            List<String> id = DivisionModel.loadDivisionID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbDivision.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(Save.getText().equals("Save")) {
            String electricity = ckbElectricity.isSelected() ? "Yes" : "No";
            String water_supply = ckbWater.isSelected() ? "Yes" : "No";

            try {
                boolean isSaved = ResidenceModel.save(new Residence(
                        txtHomeID.getText(), (String) cbDivision.getValue(), txtName.getText(), txtAddress1.getText(), Integer.valueOf(txtCount.getText()),
                        Integer.valueOf(txtChildCount.getText()), (String) cbType.getValue(), electricity, water_supply));

                if (isSaved)
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }else if(Save.getText().equals("Update")){
            String electricity = ckbElectricity.isSelected() ? "Yes" : "No";
            String water_supply = ckbWater.isSelected() ? "Yes" : "No";

            try {
                boolean isUpdated = ResidenceModel.update(new Residence(
                        txtHomeID.getText(), (String) cbDivision.getValue(), txtName.getText(), txtAddress1.getText(), Integer.valueOf(txtCount.getText()),
                        Integer.valueOf(txtChildCount.getText()), (String) cbType.getValue(), electricity, water_supply));

                if (isUpdated)
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",HomePane);
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtCount.clear();
        txtChildCount.clear();
        cbType.setValue(null);
        txtHomeID.clear();
        cbDivision.setValue(null);
        txtAddress1.clear();
        ckbWater.setSelected(false);
        ckbElectricity.setSelected(false);
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",HomePane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",HomePane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",HomePane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",HomePane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",HomePane);
    }
}
