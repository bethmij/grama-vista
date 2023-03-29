package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Civil1;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class IndividualFormController implements Initializable {


    public AnchorPane indiroot1;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtRelation;
    public Label lblCivil;
    public ChoiceBox cbGender;
    public ChoiceBox cbMarriage;
    public Button btnBack;
    public Button btnNext;
    public DatePicker dtpDOB;
    public TextField txtNIC;
    private Integer reg_id;
    public static Civil1 civil1 = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextId();
        loadGender();
        loadMarriage();
        if ((!(civil1 == null))) {
            try {
                setIndivController();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }



    private void generateNextId() {
        try {
            lblCivil.setText("C00"+CivilModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadGender() {
        String[] gender = new String[]{"Male","Female","Other"};
        ObservableList<String> dataList = FXCollections.observableArrayList(gender);
        cbGender.setItems(dataList);
    }

    private void loadMarriage() {
        String[] marriage = new String[]{"Married","Not Married"};
        ObservableList<String> dataList = FXCollections.observableArrayList(marriage);
        cbMarriage.setItems(dataList);
    }



    public void btnBackOnAction(ActionEvent actionEvent) {

        OpenView.openView("civilRegistrationForm", indiroot1);
        civil1 = null;
    }

    public void btnNextOnAction(ActionEvent actionEvent) {

        OpenView.openView("individualForm2", indiroot1);

        civil1 = new Civil1(txtName.getText(),txtNIC.getText(),txtAddress.getText(),
                dtpDOB.getValue(),(String) cbGender.getValue(),(String) cbMarriage.getValue(),txtRelation.getText());


    }

     private void setIndivController() throws SQLException {
        lblCivil.setText(String.valueOf((CivilModel.getNextId())));
        txtName.setText(civil1.getName());
        txtNIC.setText(civil1.getNic());
        txtAddress.setText(civil1.getAddress());
        dtpDOB.setValue(civil1.getDob());
        cbGender.setValue(civil1.getGender());
        cbMarriage.setValue(civil1.getMarriage());
        txtRelation.setText(civil1.getRelation());
    }

}
