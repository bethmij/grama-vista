package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.dto.Candidate;
import lk.ijse.dto.Contact;
import lk.ijse.dto.Detail;
import lk.ijse.dto.Division;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DeadModel;
import lk.ijse.model.DetailModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.IndividualFormController.civil1;

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
    public JFXButton image;
    public Button btn1;
    public Label lblName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        loadPolitic();
        loadGender();
        if ((!(CandidateManageFormController.candidate == null))) {
            setCandidateController();
        }
    }

    private void loadGender() {
        String[] gender = new String[]{"Male","Female","Other"};
        ObservableList<String> dataList = FXCollections.observableArrayList(gender);
        cbGender.setItems(dataList);
    }

    private void setCandidateController() {
        txtID.setText(CandidateManageFormController.candidate.getElection_id());
        txtAddress.setText(CandidateManageFormController.candidate.getAddress());
        txtName.setText(CandidateManageFormController.candidate.getName());
        txtNIC.setText(CandidateManageFormController.candidate.getNic());
        txtContact.setText(String.valueOf(CandidateManageFormController.candidate.getContact()));
        cbPolitic.setValue(CandidateManageFormController.candidate.getPolitical_party());
        cbDivision.setValue(CandidateManageFormController.candidate.getDivision_id());
        btn1.setText("Update");

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
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadPolitic() {
        String[] politic = new String[]{"Ahila Ilankai Thamil Congress (AITC)", "Eelam People's Democratic Party (EPDP)", "Jathika Jana balawegaya (JJB)",
                "Janatha Vimukthi Peramuna (JVP)", "Samagi Jana Balawegaya (SJB)", "Sri Lanka Freedom Party(SLFP)", "Sri Lanka Podujana Peramuna (SLPP)", "United National Party (UNP)"};
        ObservableList<String> dataList = FXCollections.observableArrayList(politic);
        cbPolitic.setItems(dataList);
    }




    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (btn1.getText().equals("Save") ) {
            if (!(cbDivision.getValue() == null) && !txtID.getText().equals("")
                    && !txtName.getText().equals("") && !txtNIC.getText().equals("") && !(cbPolitic.getValue() == null)) {
                Integer contact = null;
                if (!txtContact.getText().isEmpty())
                    contact = Integer.valueOf(txtContact.getText());
                try {
                    boolean isSaved = CandidateModel.save(new Candidate(
                            txtID.getText(),
                            (String) cbDivision.getValue(),
                            txtNIC.getText(),
                            txtName.getText(),
                            (String) cbPolitic.getValue(),
                            txtAddress.getText(),
                            contact));

                    if (isSaved)
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                cbDivision.setStyle("-fx-border-color: #ef0d20;");
                cbPolitic.setStyle("-fx-border-color:  #ef0d20; ");
                txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtNIC.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtID.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }else if (btn1.getText().equals("Update") ) {
            if (!(cbDivision.getValue() == null) && !txtID.getText().equals("")
                    && !txtName.getText().equals("") && !txtNIC.getText().equals("") && !(cbPolitic.getValue() == null)) {
                Integer contact = null;
                if (!txtContact.getText().isEmpty())
                    contact = Integer.valueOf(txtContact.getText());
                try {
                    boolean isSaved = CandidateModel.update(new Candidate(
                            txtID.getText(),
                            (String) cbDivision.getValue(),
                            txtNIC.getText(),
                            txtName.getText(),
                            (String) cbPolitic.getValue(),
                            txtAddress.getText(),
                            contact));

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
                    } else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                cbDivision.setStyle("-fx-border-color: #ef0d20;");
                cbPolitic.setStyle("-fx-border-color:  #ef0d20; ");
                txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtNIC.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtID.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }
    }







    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",CandidatePane);
    }


    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact.getText().matches("^[0-9]*$")) {
            txtContact.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
        } else if (!(txtContact.getText().length() == 10)) {
            txtContact.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("Not a valid contact number!");
        }
    }


    public void txtContactOnKeyTyped(KeyEvent keyEvent) {
        if (txtContact.getText().matches("^[0-9]*$")) {
            txtContact.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact.setText("");
        } else if (txtContact.getText().length() == 10) {
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

    @FXML
    void lblLogoutOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            Detail detail = new Detail("Logged out", "bethmi",null,null, LocalTime.now(), LocalDate.now());
            try {
                boolean isSaved = DetailModel.save(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",CandidatePane);
        }

    }


    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",CandidatePane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",CandidatePane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",CandidatePane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",CandidatePane);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        if (!txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblName.setText("This filed can not contain numeric values!");
        }
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        if (txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblName.setText("");
        }
    }
}
