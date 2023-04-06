package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.dto.MultiResidence;
import lk.ijse.model.ResidenceModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.CivilManageFormController.civil;

public class AddResidenceFormController implements Initializable {
    public ChoiceBox cbResidence;
    public static List<MultiResidence> residenceList = new ArrayList<>();
    public Label lblCivil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadResidenceID();
        lblCivil.setText(IndividualFormController.civil_id);
        if ((!(civil == null))) {
            if ((!(CivilManageFormController.multiResidenceList.get(1) == null))) {
                lblCivil.setText("C00" + CivilManageFormController.civil.getId());
                cbResidence.setValue(CivilManageFormController.multiResidenceList.get(1).getResidence_id());
            }
        }
    }

    private void loadResidenceID() {
        try {
            List<String> id = ResidenceModel.loadResidenceID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbResidence.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent)  {

        String[] strings = lblCivil.getText().split("C00");

        residenceList.add(new MultiResidence((String) cbResidence.getValue(),strings[1]));
        if(residenceList!=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
    }


}
