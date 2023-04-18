package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.dto.UserDTO;
import lk.ijse.model.UserModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static lk.ijse.controller.LoginFormController.user;

public class PasswordFormController {


    public TextField txtEmail;

    public void btnSendOnAction(ActionEvent actionEvent) {
        try {
            UserDTO userDTO = UserModel.searchbyUser(user);
            System.out.println(userDTO);
                if(userDTO.getEmail().equals(txtEmail.getText())){
                    String message =  "Employee ID  -  "+userDTO.getEmployee()+"\n" +
                                        "Username      -  "+userDTO.getUser()+"\n" +
                                        "Password       -  "+userDTO.getPassword();
                    String subject = "Grama Vista : Password Confirmation";
                    String to = userDTO.getEmail();
                    String from = "gramavista@gmail.com";
                    sendAttach(message,subject,to,from);
                    new Alert(Alert.AlertType.CONFIRMATION,"We sent you an email with your password").show();
                }else {
                    System.out.println("dfsd");
                    new Alert(Alert.AlertType.ERROR,"Invalid Email. Try Again!").show();
                }

        } catch (SQLException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
            m.setContent(mimeMultipart);
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sent success...................");

    }
}
