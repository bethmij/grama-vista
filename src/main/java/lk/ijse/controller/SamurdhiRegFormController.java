package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.tm.SamurdhiTM;



public class SamurdhiRegFormController {
    public TextField txtHome;
    public TextField txtNIC;
    public TextField txtHouse;
    public TextField txtCivil;
    public TextField txtMember;
    public TextField txtAddress;
    public static SamurdhiTM samurdhiTM;

    public void btnSaveOnAction(ActionEvent actionEvent) {

        samurdhiTM = new SamurdhiTM(txtHome.getText(), txtCivil.getText(),txtHouse.getText(), txtNIC.getText(),txtAddress.getText(),Integer.valueOf(txtMember.getText()) );
        new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated !").show();

    }

}
