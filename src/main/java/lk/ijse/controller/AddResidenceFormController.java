package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import lk.ijse.bo.custom.AddResidenceBO;
import lk.ijse.bo.custom.impl.AddResidenceBOImpl;
import lk.ijse.dto.MultiResidenceDTO;
import lk.ijse.entity.MultiResidence;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import static lk.ijse.controller.CivilManageFormController.civilDTO;

public class AddResidenceFormController implements Initializable {
    public ChoiceBox cbResidence;
    public static List<MultiResidenceDTO> residenceList = new ArrayList<>();
    public Label lblCivil;
   AddResidenceBO addResidenceBO = new AddResidenceBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadResidenceID();
        lblCivil.setText(IndividualFormController.civil_id);
        if ((!(civilDTO == null))) {
            if ((!(CivilManageFormController.multiResidenceList.get(1) == null))) {
                lblCivil.setText("C00" + civilDTO.getID());
                cbResidence.setValue(CivilManageFormController.multiResidenceList.get(1).getResidence_id());
            }
        }
    }

    private void loadResidenceID() {
        try {
            List<String> id = addResidenceBO.loadResidenceID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbResidence.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent)  {

        String[] strings = lblCivil.getText().split("C00");

        residenceList.add(new MultiResidenceDTO((String) cbResidence.getValue(),strings[1]));
        if(residenceList!=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
    }


}
