package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.CivilModel;
import lk.ijse.util.OpenView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public AnchorPane dashboardpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendEmail();
    }

    public void btnCivilRegOnAction(ActionEvent actionEvent) throws IOException {
        OpenView.openView("registrationForm",dashboardpane);
    }

    public void btnManageOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",dashboardpane);
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        OpenView.openView("reportForm",dashboardpane);
    }

    public void btnAboutOnAction(ActionEvent actionEvent) {
        OpenView.openView("aboutUsForm",dashboardpane);
    }

    public void btnVoteOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteDashboadForm",dashboardpane);

    }

    public void sendEmail() {
        try {
            Map<Integer, Integer> dateDiff = CivilModel.getDateDiff();

            for (Map.Entry m : dateDiff.entrySet()) {
                Integer date = (Integer) m.getValue();
                boolean isMailSent = CivilModel.isMailSent(m.getKey());

                if (date == 351 || date == 358) {
                    if (!isMailSent) {
                        Integer id = (Integer) m.getKey();
                        String email = CivilModel.getEmail(id);
                        String name = CivilModel.getName(String.valueOf(id));

                        System.out.println("preparing to send message ...");
                        String message = "Civil ID  - C00" + m.getKey() + "\nName   - " + name;
                        String subject = "Grama Vista : civil data renewal";
                        String to = email;
                        String from = "gramavista@gmail.com";
                        sendAttach(message, subject, to, from, id);
                    }
                }
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private static void sendAttach(String message, String subject, String to, String from, Integer id) {

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
            String path="C:\\Users\\bethm\\Downloads\\REQUEST LETTER  .pdf";

            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart fileMime = new MimeBodyPart();

            try {
                textMime.setText(message);

                File file=new File(path);
                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            } catch (Exception e) {
                e.printStackTrace();
            }
            m.setContent(mimeMultipart);
            Transport.send(m);

        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        System.out.println("Sent success...................");
        try {
            CivilModel.updateEmail(id,to);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnActivityOnAction(ActionEvent actionEvent) {
        OpenView.openView("DetailForm");
    }
}
