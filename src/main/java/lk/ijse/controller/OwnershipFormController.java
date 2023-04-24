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
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.LandFormController.index;
import static lk.ijse.controller.LandManageFormController.*;

public class OwnershipFormController implements Initializable {
    public AnchorPane ownerRoot;
    public TextField txtLotNum;
    public ChoiceBox cbCivilID;
    public TextField txtPercentage;
    public static List<Owner> ownerLists = new ArrayList<>();
    public Label lblLand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        lblLand.setText(LandFormController.land_id);
       /* if(ownerList!=null) {
            setOwnerController();
        }*/
    }


    /*public void setOwnerController() {

        lblLand.setText(String.valueOf(ownerList.get(index).getLand_id()));
        cbCivilID.setValue(ownerList.get(index).getCivil_id());
        txtPercentage.setText(String.valueOf(ownerList.get(index).getPercentage()));
        txtLotNum.setText(ownerList.get(index).getLot_num());
    }*/




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
        cbCivilID.setItems(dataList);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        String[] land_num = lblLand.getText().split("L00");
        String[] civil_id = String.valueOf(cbCivilID.getValue()).split("C00");

        ownerLists.add(new Owner(Integer.valueOf(land_num[1]), civil_id[1], txtLotNum.getText(), Double.valueOf(txtPercentage.getText() )));
        if(ownerLists!=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();

    }


}
