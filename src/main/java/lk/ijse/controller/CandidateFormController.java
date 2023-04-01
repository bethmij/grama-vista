package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.dto.Candidate;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CandidateFormController implements Initializable {


    public AnchorPane CandidatePane;
    public TextField txtID;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtContact;
    public ChoiceBox cbDivision;
    public ChoiceBox cbPolitic;
    public ChoiceBox cbGender;
    public Label lblContact;
    public Label lblAge;
    public Label lblNIC;
    public JFXButton image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        loadGender();
        loadPolitic();
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

    private void loadPolitic() {
        String[] politic = new String[]{"Ahila Ilankai Thamil Congress (AITC)","Eelam People's Democratic Party (EPDP)","Jathika Jana balawegaya (JJB)",
                "Janatha Vimukthi Peramuna (JVP)","Samagi Jana Balawegaya (SJB)","Sri Lanka Freedom Party(SLFP)","Sri Lanka Podujana Peramuna (SLPP)","United National Party (UNP)"};
        ObservableList<String> dataList = FXCollections.observableArrayList(politic);
        cbPolitic.setItems(dataList);
    }

    private void loadGender() {
        String[] gender = new String[]{"Male","Female","Other"};
        ObservableList<String> dataList = FXCollections.observableArrayList(gender);
        cbGender.setItems(dataList);
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            boolean isSaved = CandidateModel.save(new Candidate(
                    txtID.getText(),
                    (String) cbDivision.getValue(),
                    txtNIC.getText(),
                    txtName.getText(),
                    (String)cbPolitic.getValue(),
                    (String) cbGender.getValue(),
                    txtAddress.getText(),
                    Integer.valueOf(txtContact.getText())));

            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
            else
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",CandidatePane);
    }


    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact.getText().matches("^[0-9]*$")) {
            txtContact.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
        } else if (txtContact.getText().length() > 10) {
            txtContact.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("Not a valid contact number!");
        }
    }

    public void txtContactOnKeyTyped(KeyEvent keyEvent) {
        if (txtContact.getText().matches("^[0-9]*$")) {
            txtContact.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact.setText("");
        } else if (txtContact.getText().length() <= 10) {
            txtContact.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact.setText("");
        }
    }


    public void btnImageOnAction(ActionEvent actionEvent) throws FileNotFoundException {

        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
        InputStream in = new FileInputStream(file);

        try {
            boolean isUploaded = CandidateModel.upload(txtID.getText(),in);

            if (isUploaded)
                new Alert(Alert.AlertType.CONFIRMATION, "Image Uploaded Successfully !").show();
            else
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtID.clear();
        txtAddress.clear();
        txtName.clear();
        txtNIC.clear();
        txtContact.clear();
        lblContact.setText("");
    }
}
