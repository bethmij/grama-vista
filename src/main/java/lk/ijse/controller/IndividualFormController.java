package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Civil1;
import lk.ijse.dto.MultiResidence;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.model.ResidenceModel;
import lk.ijse.util.OpenView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.CivilManageFormController.civil;
import static lk.ijse.controller.CivilManageFormController.multiResidenceList;

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
    public ChoiceBox cbResidence;
    private Integer reg_id;
    public static Civil1 civil1 = null;
    public static List<MultiResidence> residenceList;
    public static String civil_id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextId();
        loadGender();
        loadMarriage();
        loadResidenceID();
        if ((!(civil1 == null))) {
            try {
                setIndivController();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        if ((!(civil == null))) {
            setCivilController();
        }

    }

    private void setCivilController() {
         txtName.setText(civil.getName());
         txtAddress.setText(civil.getAddress());
         txtRelation.setText(civil.getRelation());
         lblCivil.setText("C00"+ civil.getId());
         cbGender.setValue(civil.getGender());
         cbMarriage.setValue(civil.getMarriage());
         dtpDOB.setValue(civil.getDob());
         txtNIC.setText(civil.getNic());
         if(null != multiResidenceList.get(0)) {
             cbResidence.setValue(multiResidenceList.get(0).getResidence_id());
         }else
             cbResidence.setValue("");
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

        OpenView.openView("registrationForm", indiroot1);
        civil1 = null;
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        civil_id=lblCivil.getText();
        OpenView.openView("individualForm2", indiroot1);

        String id =  lblCivil.getText();
        String[] strings = id.split("C00");

        civil1 = new Civil1(strings[1],txtName.getText(),txtNIC.getText(),txtAddress.getText(),
                dtpDOB.getValue(),(String) cbGender.getValue(),(String) cbMarriage.getValue(),txtRelation.getText(),(String)cbResidence.getValue());


    }

     private void setIndivController() throws SQLException {
        lblCivil.setText(civil_id);
        txtName.setText(civil1.getName());
        txtNIC.setText(civil1.getNic());
        txtAddress.setText(civil1.getAddress());
        dtpDOB.setValue(civil1.getDob());
        cbGender.setValue(civil1.getGender());
        cbMarriage.setValue(civil1.getMarriage());
        txtRelation.setText(civil1.getRelation());
        cbResidence.setValue(civil1.getResidence());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        civil_id=lblCivil.getText();
        OpenView.openView("addResidenceForm");
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        dtpDOB.setValue(null);
        cbGender.setValue(null);
        cbMarriage.setValue(null);
        txtRelation.clear();
        cbResidence.setValue(null);
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",indiroot1);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",indiroot1);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",indiroot1);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {

    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {

    }
}
