package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.AboutUsBO;
import lk.ijse.bo.custom.impl.AboutUsBOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AboutUsFormController implements Initializable {
    public AnchorPane tblDivPane;
    public Label lblPopulation;
    public Label lblMale;
    public Label lblFemale;
    public Label lblHome;
    public Label lblLand;
    AboutUsBO aboutUsBO = new AboutUsBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lblPopulation.setText(String.valueOf(aboutUsBO.getPopulation()));
            lblMale.setText(String.valueOf(aboutUsBO.getMale()));
            lblFemale.setText(String.valueOf(aboutUsBO.getFemale()));
            lblHome.setText(String.valueOf(aboutUsBO.getResidenceCount()));
            lblLand.setText(String.valueOf(aboutUsBO.getLandCount()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lblLogoutOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                boolean isSaved = aboutUsBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    public void lblManageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void lblReportOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reportForm",tblDivPane);
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

    public void lblRegOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    public void btnRenewOnAction(ActionEvent actionEvent) {
        try {
            List<CivilDTO> userDTOList = aboutUsBO.searchCivil();

            for (CivilDTO user : userDTOList) {
                if(!user.getEmail().equals("")) {
                    System.out.println("preparing to send message ...");
                    String message = "Civil ID  - C00" + user.getID() + "\nName   - " + user.getName();
                    String subject = "Grama Vista : civil data renewal";
                    String to = user.getEmail();
                    String from = "gramavista@gmail.com";
                    sendAttach(message, subject, to, from);
                }
            }
        }catch (SQLException e) {
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
            e.printStackTrace();
        }

        System.out.println("Sent success...................");

    }


    public void btnActivityOnAction(ActionEvent actionEvent) {
        OpenView.openView("DetailForm");
    }
}
