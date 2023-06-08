package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.custom.IndividualFormBO;
import lk.ijse.bo.custom.impl.IndividualFormBOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.ContactDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MultiResidenceDTO;
import lk.ijse.dao.custom.impl.util.OpenView;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.AddResidenceFormController.residenceList;
import static lk.ijse.controller.CivilManageFormController.*;
import static lk.ijse.controller.IndividualFormController.civil1DTO;

public class individualForm2Controller implements Initializable {

    public AnchorPane indiroot2;
    public TextField txtSchool;
    public TextField txtSalary;
    public TextField txtWork;
    public TextField txtOccupation;
    public ChoiceBox cbEdu;
    public TextField txtContact2;
    public TextField txtContact1;
    public Label lblContact;
    public Label lblContact1;
    public Button btn1;
    public JFXButton image;

    private Boolean isCameraEnabled = true;// for cam capture
    public ImageView img;  //create image view to set camera capture
    public JFXButton btnSave;  //button set on camera on takepicture
    private volatile boolean isCapturing; //thread for button
    private FrameGrabber grabber; //to capture video frames from a webcam:
    private Java2DFrameConverter converter; //convert the video frames
    private BufferedImage imageCap;//used for loading, storing, and manipulating images
    IndividualFormBO individualFormBO = new IndividualFormBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadGender();
        if ((!(civilDTO == null))) {
            setCivilController();
        }
    }

    private void setCivilController() {
        if(civilDTO.getSchool()!=null)
            txtSchool.setText(civilDTO.getSchool());
        if(civilDTO.getSalary()!=null)
            txtSalary.setText(String.valueOf(civilDTO.getSalary()));
        if(civilDTO.getWork()!=null)
            txtWork.setText(civilDTO.getWork());
        if(civilDTO.getOccupation()!=null)
            txtOccupation.setText(civilDTO.getOccupation());
        if(civilDTO.getEducation()!=null)
            cbEdu.setValue(civilDTO.getEducation());
        if(contactList.size()==1)
            txtContact2.setText(String.valueOf(contactList.get(0).getContact()));

        if(contactList.size()==2)
            txtContact1.setText(String.valueOf(contactList.get(1).getContact()));

        btn1.setText("Update");

    }

    private void loadGender() {
        String[] education = new String[]{"Student","Employed","Unemployed"};
        ObservableList<String> dataList = FXCollections.observableArrayList(education);
        cbEdu.setItems(dataList);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        if (btn1.getText().equals("Save") ) {

            Double salary = null;
            if (!txtSalary.getText().isEmpty()) salary = Double.valueOf(txtSalary.getText());

            CivilDTO civil = new CivilDTO(civil1DTO.getId(),null ,civil1DTO.getName(), civil1DTO.getNic(), civil1DTO.getAddress(), civil1DTO.getDob(),null,
                    civil1DTO.getGender(), civil1DTO.getMarriage(), civil1DTO.getRelation(), (String) cbEdu.getValue(), txtSchool.getText(),
                    txtOccupation.getText(), txtWork.getText(), salary, civil1DTO.getEmail());

            List<ContactDTO> contactList = new ArrayList<>();

            if (!txtContact1.getText().isEmpty())
                contactList.add(new ContactDTO(civil1DTO.getId(), Integer.valueOf(txtContact1.getText())));
            if (!txtContact2.getText().isEmpty())
                contactList.add(new ContactDTO(civil1DTO.getId(), Integer.valueOf(txtContact2.getText())));

            residenceList.add(new MultiResidenceDTO(civil1DTO.getResidence(), civil1DTO.getId()));



            boolean isSaved = false;
            try {
                isSaved = individualFormBO.saveCivil(civil, contactList, residenceList);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            if (isSaved) {
                DetailDTO detail = new DetailDTO("Registration", "bethmi", LocalTime.now(), LocalDate.now(), "Registering civil id - "+ civil1DTO.getId()+" \nname - "+ civil1DTO.getName());
                try {
                    individualFormBO.saveDetail(detail);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
            }else
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } else if (btn1.getText().equals("Update") ) {

            Double salary = null;
            if (!txtSalary.getText().isEmpty()) salary = Double.valueOf(txtSalary.getText());


            CivilDTO civil = new CivilDTO(civil1DTO.getId(), null,civil1DTO.getName(), civil1DTO.getNic(), civil1DTO.getAddress(), civil1DTO.getDob(),null,
                    civil1DTO.getGender(), civil1DTO.getMarriage(), civil1DTO.getRelation(), (String) cbEdu.getValue(),
                    txtSchool.getText(), txtOccupation.getText(), txtWork.getText(), salary, civil1DTO.getEmail());

            List<ContactDTO> contactList = new ArrayList<>();

            if (!txtContact1.getText().isEmpty())
                contactList.add(new ContactDTO(civil1DTO.getId(), Integer.valueOf(txtContact1.getText())));
            if (!txtContact2.getText().isEmpty())
                contactList.add(new ContactDTO(civil1DTO.getId(), Integer.valueOf(txtContact2.getText())));

            residenceList.add(new MultiResidenceDTO(civil1DTO.getResidence(), civil1DTO.getId()));


            boolean isUpdated = false;
            try {
                DetailDTO detail = new DetailDTO("Updation", "bethmi", LocalTime.now(), LocalDate.now(), "Updating civil id - "+ civil1DTO.getId()+" \nname - "+ civil1DTO.getName());
                try {
                    individualFormBO.saveDetail(detail);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
                isUpdated = individualFormBO.updateCivil(civil, contactList, residenceList);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
                } else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
           
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws SQLException {
        OpenView.openView("individualForm",indiroot2);

    }

    public void btnImageOnAction(ActionEvent actionEvent) {

            Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(window);
            actionEvent.consume();
            InputStream in = null;
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            try {
                Integer id = individualFormBO.getCivilId(civil1DTO.getNic());
                boolean isUploaded = individualFormBO.uploadImage(String.valueOf(id), in);

                if (isUploaded)
                    new Alert(Alert.AlertType.CONFIRMATION, "Image Uploaded Successfully !").show();
                else
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

    }

    public void txtContact1OnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact1.getText().matches("^[0-9]*$")) {
            txtContact1.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact1.setText("This filed can only contain numeric values!");
        }else if (!(txtContact1.getText().length() == 10)) {
            txtContact1.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact1.setText("Not a valid contact number!");
        }
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        if (!txtContact2.getText().matches("^[0-9]*$")) {
            txtContact2.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("This filed can only contain numeric values!");
        }else if (!(txtContact2.getText().length() == 10)) {
            txtContact2.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblContact.setText("Not a valid contact number!");
        }
    }

    public void txtContact1OnKeyTyped(KeyEvent keyEvent) {
        if (txtContact1.getText().matches("^[0-9]*$")) {
            txtContact1.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact1.setText("");
        } else if (txtContact1.getText().length() > 10) {
            txtContact1.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact1.setText("");
        }
    }

    public void txtContactOnKeyTyped(KeyEvent keyEvent) {
        if (txtContact2.getText().matches("^[0-9]*$")) {
            txtContact2.setStyle("-fx-border-color: null; -fx-font-size: 16px;");
            lblContact.setText("");
        } else if (txtContact2.getText().length() > 10) {
            txtContact2.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblContact.setText("");
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtSchool.clear();
        txtSalary.clear();
        txtWork.clear();
        txtOccupation.clear();
        cbEdu.setValue(null);
        txtContact2.clear();
        txtContact1.clear();
        lblContact.setText("");
        lblContact1.setText("");
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                individualFormBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",indiroot2);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",indiroot2);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",indiroot2);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",indiroot2);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",indiroot2);
    }

    public void btnImageCapOnAction(ActionEvent actionEvent) {

        OpenView.openView("camera");
    }
}

