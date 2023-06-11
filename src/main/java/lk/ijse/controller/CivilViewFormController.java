package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.CivilViewBO;
import lk.ijse.bo.custom.impl.CivilViewBOImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.CivilManageFormController.*;

public class CivilViewFormController implements Initializable {
    public Label lblNic;
    public Label lblAddress;
    public Label lblContact;
    public Label lblAge;
    public ImageView img;
    public Label lblCivil;
    public Circle circle;
    public Label lblName;
    public Label lblDOB;
    public Label lblMarriage;
    public Label lblEdu;
    public Label lblOccupation;
    public Label lblSchool;
    public Label lblContact2;
    public Label lblWork;
    CivilViewBO civilViewBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CIVILVIEWBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            InputStream is = null;
            if(civil2DTO.getImage()==null) {
                    is = new FileInputStream("D:\\grama-vista\\src\\main\\resources\\img\\no-profile-pic-icon-11.jpg");

                }else{
                    is = civil2DTO.getImage().getBinaryStream();

                }
            Image image = new Image(is);
            circle.setFill(new ImagePattern(image));



        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();;
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        lblNic.setText(civil2DTO.getNic());
        lblAddress.setText(civil2DTO.getAddress());
        lblContact.setText(String.valueOf(civil2DTO.getContact1()));
        lblContact2.setText(String.valueOf(civil2DTO.getContact2()));
        lblAge.setText(String.valueOf(civil2DTO.getAge()));
        lblCivil.setText(civil2DTO.getId());
        lblName.setText(civil2DTO.getName());
        lblDOB.setText(String.valueOf(civil2DTO.getDob()));
        lblMarriage.setText(civil2DTO.getMarriage());
        lblEdu.setText(civil2DTO.getEdu_status());
        lblOccupation.setText(civil2DTO.getOccupation());
        if (!civil2DTO.getSchool().isEmpty())
            lblSchool.setText(civil2DTO.getSchool());
        else if (!civil2DTO.getWorking_address().isEmpty())
            lblWork.setText(civil2DTO.getWorking_address());

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {

                boolean isDeleted = civilViewBO.deleteCivil(id);

                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION," Removed Successfully !" ).show();
                }
            } catch (SQLException | ClassNotFoundException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }

        }
    }


}
