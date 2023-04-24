package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.UserDTO;
import lk.ijse.model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import static lk.ijse.controller.LoginFormController.user;

public class PasswordFormController implements Initializable {


    public TextField txtEmail;
    public TextField txtVerification;
    public TextField txtPassword;
    public Integer code;
    public JFXButton btnSave1;
    public Label lblEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendEmail();
        txtPassword.setDisable(true);
        btnSave1.setDisable(true);
        try {
            UserDTO userDTO = UserModel.searchbyUser(user);
            char letter = userDTO.getEmail().charAt(0);
            lblEmail.setText(letter+"******@gmail.com");

        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }


    private static void sendAttach(String message, String subject, String to, String from) {

        String host="smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gramavista@gmail.com", "tcnclclmcnpcxpvj");
            }});

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart fileMime = new MimeBodyPart();

            try {
                textMime.setText(message);
                mimeMultipart.addBodyPart(textMime);

            } catch (Exception e) {
               new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            m.setContent(mimeMultipart);
            Transport.send(m);

        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        System.out.println("Sent success...................");

    }

    public void sendEmail (){
        UserDTO userDTO = null;
        try {
            userDTO = UserModel.searchbyUser(user);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        Random random = new Random();
        code = random.nextInt(99999-11111) + 11111;

        String message =  "Employee ID  -  "+userDTO.getEmployee()+"\n" +
                "Username      -  "+userDTO.getUser()+"\n" +
                "Verification Code  -  "+code;
        String subject = "Grama Vista : Email verification";
        String to = userDTO.getEmail();
        String from = "gramavista@gmail.com";
        sendAttach(message,subject,to,from);
        new Alert(Alert.AlertType.CONFIRMATION,"Please check your inbox for verification code we sent to you").show();

    }

    public void btnVerifyOnAction(ActionEvent actionEvent) {

        if (code==Integer.parseInt(txtVerification.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Verification code confirmed!").show();
            txtPassword.setDisable(false);
            btnSave1.setDisable(false);
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code!").show();
        }
    }

    public void btnResendOnAction(MouseEvent mouseEvent) {
       sendEmail();
    }

    public void btnSetPassOnAction(ActionEvent actionEvent) {
        try {
            String hashed = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
            boolean isSaved = UserModel.updatePass(hashed,user);
            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION,"Password has been reset successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


}
