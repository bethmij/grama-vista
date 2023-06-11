package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.UserRegistrationBO;
import lk.ijse.bo.custom.impl.UserRegistrationBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.UserManageFormController.user;

public class UserRegistrationFormController implements Initializable {
    public AnchorPane userPane;
    public TextField txtNIC;
    public DatePicker dtpDOB;
    public TextField txtENum;
    public TextField txtPass;
    public TextField txtUser;
    public TextField txtName;
    public DatePicker dtpEmployee;
    public TextField txtContact;
    public Button btnBack;
    public Button btnSave;
    public ComboBox cbDivision;
    public Label lblContact;
    public Label lblPass;
    public TextField txtEmail;
    public Label lblEmail;
    public Label lblName;
    UserRegistrationBO userRegistrationBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USERREGISTRATIONBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        if ((!(user == null))) {
            setUserController();
        }
    }

    private void setUserController() {
        txtPass.setDisable(true);
        txtNIC.setText(user.getNic());
        dtpDOB.setValue(user.getDob());
        txtENum.setText(user.getEmployee());
        txtUser.setText(user.getUser());
        txtName.setText(user.getName());
        dtpEmployee.setValue(user.getEmDate());
        txtEmail.setText(user.getEmail());
        if(user.getContact()!=null)
            txtContact.setText(String.valueOf(user.getContact()));
        btnSave.setText("Update");
        cbDivision.setValue(user.getDivision());
    }



    private void loadDivisionID() {
        try {
            List<String> id = userRegistrationBO.loadDivision();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbDivision.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        if(btnSave.getText().equals("Save")) {
            if (!(cbDivision.getValue() == null) && !txtName.getText().equals("") && !txtNIC.getText().equals("") && !txtENum.getText().equals("")
                    && !txtUser.getText().equals("")  && !txtPass.getText().equals("")  && !txtEmail.getText().equals("") && dtpDOB.getValue()!=null && dtpEmployee.getValue()!=null) {
                Integer contact = null;
                if (!txtContact.getText().isEmpty())
                    contact = Integer.valueOf(txtContact.getText());
                try {
                    boolean isSaved = userRegistrationBO.saveUser(new UserDTO(txtENum.getText(),(String) cbDivision.getValue(), txtName.getText(), txtNIC.getText(),
                                    txtUser.getText(), txtPass.getText(), dtpDOB.getValue(),null,dtpEmployee.getValue(),contact ,txtEmail.getText() ));

                    if (isSaved)
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfuly !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();


                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                cbDivision.setStyle("-fx-border-color:  #ef0d20; ");
                txtENum.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtNIC.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtUser.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtEmail.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                dtpDOB.setStyle("-fx-border-color:  #ef0d20; ");
                dtpEmployee.setStyle("-fx-border-color:  #ef0d20; ");

                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        } else if(btnSave.getText().equals("Update")){
            if (!(cbDivision.getValue() == null) && !txtName.getText().equals("") && !txtNIC.getText().equals("") && !txtENum.getText().equals("")
                    && !txtUser.getText().equals("")  && !txtEmail.getText().equals("") && dtpDOB.getValue()!=null && dtpEmployee.getValue()!=null) {
                Integer contact = null;
                if (!txtContact.getText().isEmpty())
                    contact = Integer.valueOf(txtContact.getText());
                try {
                    boolean isUpdated = userRegistrationBO.updateUser(new UserDTO(txtENum.getText(),(String) cbDivision.getValue(), txtName.getText(), txtNIC.getText(),
                            txtUser.getText(), txtPass.getText(), dtpDOB.getValue(),null,dtpEmployee.getValue(),contact ,txtEmail.getText()));

                    if (isUpdated)
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfuly !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();


                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                cbDivision.setStyle("-fx-border-color:  #ef0d20; ");
                txtENum.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtName.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtNIC.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtUser.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                txtEmail.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
                dtpDOB.setStyle("-fx-border-color:  #ef0d20; ");
                dtpEmployee.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("loginForm", userPane);
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

    public void txtPassOnKeyReleased(KeyEvent keyEvent) {
        if (!txtPass.getText().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")) {
            txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblPass.setText("Should contain minimum eight characters with special character!");
        }
    }


    public void txtPassOnKeyTyped(KeyEvent keyEvent) {
        if (txtPass.getText().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")) {
            txtPass.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblPass.setText("");
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtNIC.clear();
        dtpDOB.setValue(null);
        txtENum.clear();
        txtPass.clear();
        txtUser.clear();
        txtName.clear();
        dtpEmployee.setValue(null);
        txtContact.clear();
        txtEmail.clear();
        cbDivision.setValue(null);
        lblContact.setText("");
        lblPass.setText("");
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

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblEmail.setText("Invalid Email Format!");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
    }

    public void txtEmailOnKeyTyped(KeyEvent keyEvent) {
        if (txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
    }
}



