package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.CivilModel;
import lk.ijse.model.LandModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OwnershipFormController implements Initializable {
    public AnchorPane ownerRoot;
    public TextField txtLotNum;
    public ChoiceBox cbLandID;
    public ChoiceBox cbCivilID;
    public TextField txtPercentage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandId();
        loadCivilId();
    }

    private void loadLandId() {
        List<String> id = null;
        try {
            id = LandModel.loadLandId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add(ids);
        }
        cbLandID.setItems(dataList);
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
        cbCivilID.setItems(dataList);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
    }


}
