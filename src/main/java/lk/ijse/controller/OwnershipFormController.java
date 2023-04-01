package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Owner;
import lk.ijse.model.CivilModel;
import lk.ijse.model.LandModel;
import lk.ijse.model.OwnerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OwnershipFormController implements Initializable {
    public AnchorPane ownerRoot;
    public TextField txtLotNum;
    public ChoiceBox cbLandID;
    public ChoiceBox cbCivilID;
    public TextField txtPercentage;
    public static List<Owner> ownerList = new ArrayList<>();
    public TextField txtLand;
    public Label lblLand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        lblLand.setText(LandFormController.land_id);
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

        String[] land_num = lblLand.getText().split("L00");
        String[] civil_id = String.valueOf(cbCivilID.getValue()).split("C00");

        ownerList.add(new Owner(Integer.valueOf(land_num[1]), civil_id[1], txtLotNum.getText(), Double.valueOf(txtPercentage.getText() )));
        if(ownerList!=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();

    }



}
