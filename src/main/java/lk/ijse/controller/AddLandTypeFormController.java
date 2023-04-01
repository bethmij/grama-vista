package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lk.ijse.dto.LandType;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddLandTypeFormController implements Initializable {


    public TextField txtLand;
    public ChoiceBox cbType;
    public static List<LandType> landTypeList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandType();
    }

    private void loadLandType() {
        String[] type = new String[]{"Government","Non Government","Cultivated", "Uncultivated"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbType.setItems(dataList);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        landTypeList.add(new LandType(txtLand.getText(), (String) cbType.getValue()));
        if(landTypeList!=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
    }


}
