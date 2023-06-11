package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.CameraBO;
import lk.ijse.bo.custom.impl.CameraBOImpl;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.controller.IndividualFormController.civil1DTO;

public class CameraFormController implements Runnable , Initializable {


    private Boolean isCameraEnabled = true;// for cam capture
    public ImageView img;  //create image view to set camera capture
    public JFXButton btnSave;  //button set on camera on takepicture
    private volatile boolean isCapturing; //thread for button
    private FrameGrabber grabber; //to capture video frames from a webcam:
    private Java2DFrameConverter converter; //convert the video frames
    private BufferedImage image;//used for loading, storing, and manipulating images
    CameraBO cameraBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CAMERABO);;

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String imageName = civil1DTO.getId() + ".png"; // Construct the file name
        File outputFile = new File(imageName);
        try {
            ImageIO.write(image, "png", outputFile);
            InputStream in = null;
            try {
                in = new FileInputStream(outputFile);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            try {
                Integer id = cameraBO.getCivilId(civil1DTO.getNic());
                boolean isUploaded = cameraBO.uploadImage(String.valueOf(id), in);

                if (isUploaded)
                    new Alert(Alert.AlertType.CONFIRMATION, "Image Uploaded Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the image!").show();
            return;
        }

    }
    public void run() {
        try {
            // Continuously capture photos until isCapturing is set to false
            grabber.start();
            while (isCapturing) {
                Frame frame = grabber.grab();
                image = converter.convert(frame);
                Image fxImage = SwingFXUtils.toFXImage(image, null); // convert to JavaFX Image
                img.setImage(fxImage); // set Image to ImageView pane
            }
            grabber.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grabber = new OpenCVFrameGrabber(0);
        converter = new Java2DFrameConverter();

        isCameraEnabled = true;
        if (isCameraEnabled) {
            if (!isCapturing) {
                // Start
                isCapturing = true;
                Thread captureThread = new Thread((Runnable) this);
                captureThread.start();
                btnSave.setText("Take Picture");
                btnSave.setStyle("-fx-background-color:  #c93329;");
            } else {
                // Stop
                isCapturing = false;
                btnSave.setText("Open Camera");
                btnSave.setStyle("-fx-background-color:    #158392;");
            }

        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        isCameraEnabled = false;
        try {
            grabber.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}
