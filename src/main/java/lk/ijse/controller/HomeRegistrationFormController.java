package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.dto.Residence;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.model.ResidenceModel;
import lk.ijse.util.OpenView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

public class HomeRegistrationFormController implements Initializable {
    public AnchorPane  HomePane;
    public TextField txtName;
    public TextField txtCount;
    public TextField txtChildCount;
    public TextField txtAddress;
    public ChoiceBox cbType;
    public TextField txtHomeID;
    public CheckBox ckbElectricity;
    public CheckBox ckbWater;
    public ChoiceBox cbDivision;
    public Label lblName;
    public ChoiceBox cbCivil;
    public ComboBox cmbCivil;
    public TextField txtAddress1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        loadResiType();
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
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
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

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",HomePane);
    }

}
