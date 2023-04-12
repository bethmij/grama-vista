package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DeadModel;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.CandidateManageFormController.*;

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

                //InputStream is = candidateDetail.getImage().getBinaryStream();
                //InputStream is = new FileInputStream("D:\\grama-vista\\src\\main\\resources\\img\\no-profile-pic-icon-11.jpg");
                InputStream is = null;
                if(candidateDetail.getImage()==null){
                   is = new FileInputStream("D:\\grama-vista\\src\\main\\resources\\img\\no-profile-pic-icon-11.jpg");
                }else{
                    is = candidateDetail.getImage().getBinaryStream();
                }
                //Image image = new Image(is);
                Image image = new Image(is);

               // circle.setFill(new ImagePattern(image));
                circle.setFill(new ImagePattern(image));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        lblAddress.setText(candidateDetail.getAddress());
            lblContact.setText(String.valueOf(candidateDetail.getContact()));
            lblDivision.setText(candidateDetail.getDivision());
            lblElection.setText(candidateDetail.getElection());
            lblName.setText(candidateDetail.getName());
            lblNic.setText(candidateDetail.getNIC());
            lblPolitic.setText(candidateDetail.getPolitic());


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                boolean isDeleted = CandidateModel.delete(lblElection.getText());

                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                }
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }

        }
    }
}