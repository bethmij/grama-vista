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
import lk.ijse.bo.custom.MaternityRegistBO;
import lk.ijse.bo.custom.impl.MaternityRegistBOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.MaternityManageFormController.maternity;

public class MaternityRegistFormController implements Initializable {
    public AnchorPane maternityPane;
    public ComboBox cmbCivil;
    public TextField txtMidWife;
    public Label lblID;
    public Label lblName;
    public DatePicker dtpDate;
    public Button btnSave;
    public TextField txtMonth;
    public Label lblMidWife;
    public Label lblMonth;
    MaternityRegistBO registBO = new MaternityRegistBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilId();
        generateNextId();
        if ((!(maternity == null))) {
            setMaternityController();
        }
    }

    private void setMaternityController() {
        cmbCivil.setValue("C00"+maternity.getCivil());
        if(maternity.getMidWife()!=null)
            txtMidWife.setText(maternity.getMidWife());
        lblID.setText("M00"+maternity.getReg());
        lblName.setText(maternity.getName());
        if(maternity.getMonth()!=null)
            txtMonth.setText(String.valueOf(maternity.getMonth()));
        btnSave.setText("Update");
    }

    private void loadCivilId() {
        List<String> id = null;
        try {
            id = registBO.loadCivilId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add("C00"+ids);
        }
        cmbCivil.setItems(dataList);

    }

    private void generateNextId() {
        try {
            lblID.setText("M00"+ registBO.getNextMaternityID());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",maternityPane);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save")) {
            if (!(cmbCivil.getValue() == null) && !txtMonth.getText().equals("") ) {
                String[] id = lblID.getText().split("M00");
                String[] civil_id = String.valueOf(cmbCivil.getValue()).split("C00");

                try {
                    boolean isSaved = registBO.saveMaternity(new MaternityDTO(
                            id[1],
                            civil_id[1],
                            lblName.getText(),
                            Integer.valueOf(txtMonth.getText()),
                            txtMidWife.getText()));

                    if (isSaved) {
                        DetailDTO detail = new DetailDTO("Registration", "bethmi", LocalTime.now(), LocalDate.now(), "Registering maternity  id - " + lblID.getText() + " \nname - " + lblName.getText());
                        try {
                            registBO.saveDetail(detail);
                        } catch (SQLException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                    }else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtMonth.setStyle("-fx-border-color:  #ef0d20; ");
                cmbCivil.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }else if(btnSave.getText().equals("Update")) {
            if (!(cmbCivil.getValue() == null) && !txtMonth.getText().equals("")) {
                String[] id = lblID.getText().split("M00");
                String[] civil_id = String.valueOf(cmbCivil.getValue()).split("C00");

                try {
                    boolean isUpdated = registBO.updateMaternity(new MaternityDTO(id[1], civil_id[1], lblName.getText(), Integer.valueOf(txtMonth.getText()), txtMidWife.getText()));

                    if (isUpdated)
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtMonth.setStyle("-fx-border-color:  #ef0d20; ");
                cmbCivil.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }
    }

    public void cmbCivilOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCivil.getValue();
        String[] strings = id.split("C00");

        try {
            lblName.setText(registBO.searchCivilByID(strings[1]));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnResetOnAction(ActionEvent actionEvent) {
        txtMidWife.clear();
        lblName.setText("");
        txtMonth.clear();
        dtpDate.setValue(null);
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                registBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",maternityPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",maternityPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",maternityPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",maternityPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",maternityPane);
    }

    public void txtMonthOnKeyReleased(KeyEvent keyEvent) {
        if (!txtMonth.getText().matches("^[0-9]*$")) {
            txtMonth.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblMonth.setText("This filed can only contain numeric values!");
        }
    }

    public void txtMonthOnKeyTyped(KeyEvent keyEvent) {
        if (!txtMonth.getText().matches("^[0-9]*$")) {
            txtMonth.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblMonth.setText("");
        }
    }

    public void txtMidWifeOnKeyReleased(KeyEvent keyEvent) {
        if (!txtMidWife.getText().matches("^[A-Za-z\\s]*$")) {
            txtMidWife.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblMidWife.setText("This filed can not contain numeric values!");
        }
    }

    public void txtMidWifeOnKeyTyped(KeyEvent keyEvent) {
        if (!txtMidWife.getText().matches("^[A-Za-z\\s]*$")) {
            txtMidWife.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblMidWife.setText("");
        }
    }
}
