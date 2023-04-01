package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.dto.LandDetail;
import lk.ijse.model.LandTypeModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddLandTypeFormController implements Initializable {


    public TextField txtLand;
    public ChoiceBox cbType;
    public static List<LandDetail> landDetailList = new ArrayList<>();
    public Label lblLand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandType();
        lblLand.setText(LandFormController.land_id);
    }

    private void loadLandType() {
        String[] type = new String[]{"Government","Non Government","Cultivated", "Uncultivated"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbType.setItems(dataList);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        String[] land_num = lblLand.getText().split("L00");
        Integer type_id = LandTypeModel.getTypeId((String) cbType.getValue());

        landDetailList.add(new LandDetail(type_id, Integer.valueOf(land_num[1])));
        if(landDetailList !=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
    }


}
