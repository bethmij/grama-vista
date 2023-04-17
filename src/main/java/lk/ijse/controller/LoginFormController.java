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
import lk.ijse.util.OpenView;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginFormController {

    public PasswordField txtPass;
    public TextField txtUser;
    @FXML
    private AnchorPane root;
    public void btnOnAction(ActionEvent actionEvent)  {

       /* if (txtUser.getText().equals("Bethmi") && txtPass.getText().equals("1234")){
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
            new Alert(Alert.AlertType.ERROR,"Incorrect Username or Password").show();*/

        OpenView.openView("dashboardForm",root);
    }

    public void btnSignOnAction(MouseEvent actionEvent) {
       OpenView.openView("userRegistrationForm");
    }



    public void btn1OnAction(ActionEvent actionEvent) throws JRException {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/PregReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            e.printStackTrace();
        }

    }
}
