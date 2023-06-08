package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.IndividualBO;
import lk.ijse.bo.custom.impl.IndividualBOImpl;
import lk.ijse.dto.Civil1DTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.MultiResidence;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.CivilManageFormController.*;

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
    public TextField txtEmail;
    public Label lblEmal;
    public Label lblRelation;
    public Label lblName;
    private Integer reg_id;
    public static Civil1DTO civil1DTO = null;
    public static List<MultiResidence> residenceList;
    public static String civil_id;
    IndividualBO individualBO = new IndividualBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextId();
        loadGender();
        loadMarriage();
        loadResidenceID();
        if ((!(civil1DTO == null))) {
            try {
                setIndivController();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        if ((!(civilDTO == null))) {
            setCivilController();
        }

    }

    private void setCivilController() {
         txtName.setText(civilDTO.getName());
         txtAddress.setText(civilDTO.getAddress());
         txtRelation.setText(civilDTO.getRelation());
         lblCivil.setText("C00"+ civilDTO.getID());
         if(civilDTO.getGender()!=null)
            cbGender.setValue(civilDTO.getGender());
         if(civilDTO.getMarriage()!=null)
            cbMarriage.setValue(civilDTO.getMarriage());
         if(civilDTO.getDob()!=null)
            dtpDOB.setValue(civilDTO.getDob());
         txtNIC.setText(civilDTO.getNic());
         txtEmail.setText(civilDTO.getEmail());
         if(null != multiResidenceList.get(0)) {
             cbResidence.setValue(multiResidenceList.get(0).getResidence_id());
         }else
             cbResidence.setValue("");
    }

    private void loadResidenceID() {
        try {
            List<String> id = individualBO.loadResidenceId();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbResidence.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void generateNextId() {
        try {
            lblCivil.setText("C00"+ individualBO.getCivilNextId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        civil1DTO = null;
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        if (!(cbResidence.getValue() == null) && !txtName.getText().equals("")
                && !txtNIC.getText().equals("") && !txtAddress.getText().equals("")
                && !txtRelation.getText().equals("") && dtpDOB.getValue()!=null) {
            civil_id = lblCivil.getText();
            OpenView.openView("individualForm2", indiroot1);

            String id = lblCivil.getText();
            String[] strings = id.split("C00");

            civil1DTO = new Civil1DTO(strings[1], txtName.getText(), txtNIC.getText(), txtAddress.getText(),
                    dtpDOB.getValue(), (String) cbGender.getValue(), (String) cbMarriage.getValue(),
                    txtRelation.getText(), (String) cbResidence.getValue(), txtEmail.getText());
        }else{
            cbResidence.setStyle("-fx-border-color:  #ef0d20; ");
            txtRelation.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            txtNIC.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            txtAddress.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            dtpDOB.setStyle("-fx-border-color:  #ef0d20; ");
            new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
        }

    }

     private void setIndivController() throws SQLException {
        lblCivil.setText(civil_id);
        txtName.setText(civil1DTO.getName());
        txtNIC.setText(civil1DTO.getNic());
        txtAddress.setText(civil1DTO.getAddress());
        dtpDOB.setValue(civil1DTO.getDob());
        cbGender.setValue(civil1DTO.getGender());
        cbMarriage.setValue(civil1DTO.getMarriage());
        txtRelation.setText(civil1DTO.getRelation());
        cbResidence.setValue(civil1DTO.getResidence());
        txtEmail.setText(civil1DTO.getEmail());
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
        txtEmail.clear();
        lblEmal.setText("");
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi",LocalTime.now(), LocalDate.now(),"");
            try {
                individualBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
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
        OpenView.openView("reportForm",indiroot1);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",indiroot1);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblEmal.setText("Invalid Email Format!");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmal.setText("");
        }
    }

    public void txtEmailKeyTyped(KeyEvent keyEvent) {
        if (txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmal.setText("");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmal.setText("");
        }
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        if (!txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblName.setText("This filed can not contain numeric values!");
        }
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        if (txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-border-color: null; -fx-font-size: 16px;");
            lblName.setText("");
        }
    }

    public void txtRelationOnKeyReleased(KeyEvent keyEvent) {
        if (!txtRelation.getText().matches("^[A-Za-z\\s]*$")) {
            txtRelation.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblRelation.setText("This filed can not contain numeric values!");
        }
    }

    public void txtRelationOnKeyTyped(KeyEvent keyEvent) {
        if (txtRelation.getText().matches("^[A-Za-z\\s]*$")) {
            txtRelation.setStyle("-fx-border-color: null; -fx-font-size: 16px;");
            lblRelation.setText("");
        }
    }
}
