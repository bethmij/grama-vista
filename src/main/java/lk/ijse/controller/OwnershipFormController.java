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
import lk.ijse.bo.custom.OwnershipBO;
import lk.ijse.bo.custom.impl.OwnershipBOImpl;
import lk.ijse.dto.CoOwnerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.LandManageFormController.*;

public class OwnershipFormController implements Initializable {
    public AnchorPane ownerRoot;
    public TextField txtLotNum;
    public ChoiceBox cbCivilID;
    public TextField txtPercentage;
    public static List<CoOwnerDTO> coOwnerLists = new ArrayList<>();
    public Label lblLand;
    OwnershipBO ownershipBO = new OwnershipBOImpl();

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
            id = ownershipBO.loadCivilId();
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

        if ((!(land == null)))
            coOwnerLists.add(new CoOwnerDTO(land.getLand_id(), civil_id[1], txtLotNum.getText(), Double.valueOf(txtPercentage.getText() )));
        else
            coOwnerLists.add(new CoOwnerDTO(Integer.valueOf(land_num[1]), civil_id[1], txtLotNum.getText(), Double.valueOf(txtPercentage.getText() )));
        if(coOwnerLists !=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();

    }


}
