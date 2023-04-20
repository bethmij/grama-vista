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
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Dead;
import lk.ijse.dto.UserDTO;
import lk.ijse.model.CivilModel;
import lk.ijse.model.UserModel;
import lk.ijse.util.OpenView;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class LoginFormController {

    public PasswordField txtPass;
    public TextField txtUser;
    public static String user = null;
    @FXML
    private AnchorPane root;
    public void btnOnAction(ActionEvent actionEvent) {
        /*List<UserDTO> userDTO = null;
        try {
            userDTO = UserModel.searchAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean isTrue = false;
        for (int i = 0; i < userDTO.size(); i++) {
            if (txtUser.getText().equals(userDTO.get(i).getUser()) && txtPass.getText().equals(userDTO.get(i).getPassword())) {
                isTrue =true;
            }
        }

        if (isTrue) {
            OpenView.openView("dashboardForm", root);

        } else if (txtUser.getText().equals("") || txtPass.getText().equals("")) {
            txtUser.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new animatefx.animation.Shake(txtUser).play();
            new animatefx.animation.Shake(txtPass).play();
            new Alert(Alert.AlertType.ERROR, "Please enter your Username and Password ").show();
        }else {
            txtUser.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            txtPass.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new animatefx.animation.Shake(txtUser).play();
            new animatefx.animation.Shake(txtPass).play();
            new Alert(Alert.AlertType.ERROR, "Incorrect Username or Password").show();
        }*/

         OpenView.openView("dashboardForm",root);
    }

    public void btnSignOnAction(MouseEvent actionEvent) {
       OpenView.openView("userRegistrationForm");
    }


    public void btnForgotOnAction(MouseEvent mouseEvent) {
        user=txtUser.getText();
        OpenView.openView("passwordForm");
    }
}
