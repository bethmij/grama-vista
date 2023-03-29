package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.model.DivisionModel;
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

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",HomePane);
    }



}
