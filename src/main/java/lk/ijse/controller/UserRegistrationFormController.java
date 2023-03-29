package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.User;
import lk.ijse.model.DivisionModel;
import lk.ijse.model.UserModel;
import lk.ijse.util.OpenView;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
    public TextField txtSalary;
    public Button btnBack;
    public Button btnSave;
    public ComboBox cbDivision;
    public Label lblContact;
    public Label lblPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
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


        try {
            boolean isSaved = UserModel.save(new User(
                    (String) cbDivision.getValue(),
                    txtENum.getText(),
                    txtNIC.getText(),
                    txtName.getText(),
                    txtUser.getText(),
                    txtPass.getText(),
                    dtpDOB.getValue(),
                    dtpEmployee.getValue(),
                    Double.valueOf(txtSalary.getText()),
                    Integer.valueOf(txtContact.getText())
                    )
            );

            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfuly !").show();
            else
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("loginForm", userPane);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {

        if (!txtContact.getText().matches("^[0-9]*$")) {
            txtContact.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
           // new animatefx.animation.Shake(txtContact).play();
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

    public void txtPassOnKeyReleased(KeyEvent keyEvent) {
        if (txtPass.getText().length() <= 4) {
            txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblPass.setText("Password Can't be less than 4 characters!");
        }
    }


    public void txtPassOnKeyTyped(KeyEvent keyEvent) {
        if (txtPass.getText().length() > 4) {
            txtPass.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblPass.setText("");
        }
    }
}



