package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.OpenView;

import java.io.IOException;

public class LoginFormController {

    public PasswordField txtPass;
    public TextField txtUser;
    @FXML
    private AnchorPane root;
    public void btnOnAction(ActionEvent actionEvent)  {

        if (txtUser.getText().equals("Bethmi") && txtPass.getText().equals("1234")){
            Stage stage = (Stage)root.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Dashboard");
            stage.centerOnScreen();
        }else if (txtUser.getText().equals("") || txtPass.getText().equals(""))
            new Alert(Alert.AlertType.ERROR,"Please enter your Username and Password ").show();
        else
            new Alert(Alert.AlertType.ERROR,"Incorrect Username or Password").show();

        //OpenView.openView("dashboardForm",root);
    }

    public void btnSignOnAction(MouseEvent actionEvent) {
       OpenView.openView("userRegistrationForm");
    }
}
