package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dto.*;
import lk.ijse.model.*;
import lk.ijse.util.OpenView;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.AddResidenceFormController.residenceList;
import static lk.ijse.controller.CivilManageFormController.civil;
import static lk.ijse.controller.CivilManageFormController.contactList;
import static lk.ijse.controller.IndividualFormController.civil1;
import static lk.ijse.controller.IndividualFormController.civil_id;

public class individualForm2Controller implements Initializable {

    public AnchorPane indiroot2;
    public TextField txtSchool;
    public TextField txtSalary;
    public TextField txtWork;
    public TextField txtOccupation;
    public ChoiceBox cbEdu;
    public TextField txtContact2;
    public TextField txtContact1;
    public Label lblContact;
    public Label lblContact1;
    public Button btn1;
    public JFXButton image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadGender();
        if ((!(civil == null))) {
            setCivilController();
        }
    }

    private void setCivilController() {
        txtSchool.setText(civil.getSchool());
        txtSalary.setText(String.valueOf(civil.getSalary()));
        txtWork.setText(civil.getWorking_address());
        txtOccupation.setText(civil.getOccupation());
        cbEdu.setValue(civil.getEdu_status());
        if(contactList.get(0)!=null) {
            txtContact2.setText(String.valueOf(contactList.get(0).getContact()));
        }
        if(contactList.get(1)!=null) {
            txtContact1.setText(String.valueOf(contactList.get(1).getContact()));
        }
        btn1.setText("Update");

    }

    private void loadGender() {
        String[] education = new String[]{"Student","Employed","Unemployed"};
        ObservableList<String> dataList = FXCollections.observableArrayList(education);
        cbEdu.setItems(dataList);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        if (btn1.getText().equals("Save") ) {

            Double salary;
            if (!txtSalary.getText().isEmpty()) salary = Double.valueOf(txtSalary.getText());
            else salary = 0.0;



            Civil civil = new Civil(civil1.getId(), civil1.getName(), civil1.getNic(), civil1.getAddress(), civil1.getDob(),
                    civil1.getGender(), civil1.getMarriage(), civil1.getRelation(), (String) cbEdu.getValue(), txtSchool.getText(),
                    txtOccupation.getText(), txtWork.getText(), salary);

            List<Contact> contactList = new ArrayList<>();

            if (!txtContact1.getText().isEmpty())
                contactList.add(new Contact(civil1.getId(), Integer.valueOf(txtContact1.getText())));
            if (!txtContact2.getText().isEmpty())
                contactList.add(new Contact(civil1.getId(), Integer.valueOf(txtContact2.getText())));

            residenceList.add(new MultiResidence(civil1.getResidence(), civil1.getId()));

            String division_id = CivilModel.getDivisionId(civil1.getResidence());

            boolean isSaved = false;
            try {
                isSaved = CivilResidenceModel.save(civil, contactList, residenceList, division_id);
            } catch (SQLException e) {
                System.out.println(e);
            }

            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
            else
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        } else if (btn1.getText().equals("Update") ) {

            Double salary;
            if (!txtSalary.getText().isEmpty()) salary = Double.valueOf(txtSalary.getText());
            else salary = 0.0;


            Civil civil = new Civil(civil1.getId(), civil1.getName(), civil1.getNic(), civil1.getAddress(), civil1.getDob(),
                    civil1.getGender(), civil1.getMarriage(), civil1.getRelation(), (String) cbEdu.getValue(),
                    txtSchool.getText(), txtOccupation.getText(), txtWork.getText(), salary);

            List<Contact> contactList = new ArrayList<>();

            if (!txtContact1.getText().isEmpty())
                contactList.add(new Contact(civil1.getId(), Integer.valueOf(txtContact1.getText())));
            if (!txtContact2.getText().isEmpty())
                contactList.add(new Contact(civil1.getId(), Integer.valueOf(txtContact2.getText())));

            residenceList.add(new MultiResidence(civil1.getResidence(), civil1.getId()));


            boolean isUpdated = false;
            try {
                isUpdated = CivilResidenceModel.update(civil, contactList, residenceList);
            } catch (SQLException e) {
                System.out.println(e);
            }
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
                } else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
           
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws SQLException {
        OpenView.openView("individualForm",indiroot2);

    }

    public void btnImageOnAction(ActionEvent actionEvent) {

            Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(window);
            actionEvent.consume();
            InputStream in = null;
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            try {
                String[] strings = civil_id.split("C00");
                boolean isUploaded = CivilModel.upload(strings[1], in);

                if (isUploaded)
                    new Alert(Alert.AlertType.CONFIRMATION, "Image Uploaded Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

    }

    public void txtContact1OnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact1.getText().matches("^[0-9]*$")) {
            txtContact1.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
        }
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact2.getText().matches("^[0-9]*$")) {
            txtContact2.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
        }
    }

    public void txtContact1OnKeyTyped(KeyEvent keyEvent) {
        if (txtContact1.getText().matches("^[0-9]*$")) {
            txtContact1.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact.setText("");
        }
    }

    public void txtContactOnKeyTyped(KeyEvent keyEvent) {
        if (txtContact2.getText().matches("^[0-9]*$")) {
            txtContact2.setStyle("-fx-border-color: null; -fx-font-size: 16px;");
            lblContact.setText("");
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtSchool.clear();
        txtSalary.clear();
        txtWork.clear();
        txtOccupation.clear();
        cbEdu.setValue(null);
        txtContact2.clear();
        txtContact1.clear();
        lblContact.setText("");
        lblContact1.setText("");
    }
}
