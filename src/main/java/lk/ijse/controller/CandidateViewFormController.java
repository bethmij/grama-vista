package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.controller.CandidateManageFormController.candidate2TM;
import static lk.ijse.controller.CandidateManageFormController.candidateTM;

public class CandidateViewFormController implements Initializable {

    public Circle circle;
    @FXML
    private ImageView img;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblDivision;

    @FXML
    private Label lblElection;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblPolitic;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            InputStream is = candidateTM.getImage().getBinaryStream();
            Image image = new Image(is);

            //img.setImage(image);
            circle.setFill(new ImagePattern(image));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lblAddress.setText(candidate2TM.getAddress());
        lblContact.setText(String.valueOf(candidate2TM.getContact()));
        lblDivision.setText(candidateTM.getDivision());
        lblElection.setText(candidateTM.getElection());
        lblName.setText(candidateTM.getName());
        lblNic.setText(candidateTM.getNIC());
        lblPolitic.setText(candidate2TM.getPolitic());

    }
}