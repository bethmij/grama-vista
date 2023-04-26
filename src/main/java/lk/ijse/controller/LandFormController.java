package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Detail;
import lk.ijse.dto.Land;
import lk.ijse.dto.LandDetail;
import lk.ijse.model.DetailModel;
import lk.ijse.model.LandModel;
import lk.ijse.model.LandTypeModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.CivilManageFormController.multiResidenceList;
import static lk.ijse.controller.LandManageFormController.*;
import static lk.ijse.controller.MaternityManageFormController.maternity;

public class LandFormController implements Initializable {
    public AnchorPane landRoot;
    public TextField txtPlan;
    public TextField txtArea;
    public ChoiceBox cbLType;
    public Label lblID;
    public static String land_id;
    public Button save;
    public static Integer index;
    public Label lblArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandType();
        generateLandId();
        if ((!(land == null))) {
            setLandController();
        }
    }

    private void setLandController() {
        txtPlan.setText(land.getPlan_num());
        txtArea.setText(String.valueOf(land.getL_area()));
        lblID.setText(String.valueOf(land.getLand_id()));
        save.setText("Update");



    }

    private void generateLandId() {
        try {
            String id = "L00"+LandModel.getNextLandId();
            lblID.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadLandType() {
        String[] type = new String[]{"Government","Non Government","Cultivated", "Uncultivated"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbLType.setItems(dataList);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        if(save.getText().equals("Save")) {
            if (!txtPlan.getText().equals("") && !txtArea.getText().equals("")) {
                String[] land_num = lblID.getText().split("L00");
                Integer type_id = LandTypeModel.getTypeId((String) cbLType.getValue());

                AddLandTypeFormController.landDetailList.add(new LandDetail(type_id, Integer.valueOf(land_num[1]), (String) cbLType.getValue()));

                try {
                    boolean isSaved = LandModel.save(new Land(
                            Integer.valueOf(land_num[1]), txtPlan.getText(), Double.valueOf(txtArea.getText())), AddLandTypeFormController.landDetailList, OwnershipFormController.ownerLists);

                    if (isSaved)
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtArea.setStyle("-fx-border-color:  #ef0d20; ");
                txtPlan.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }else if(save.getText().equals("Update")){
            if (!txtPlan.getText().equals("") && !txtArea.getText().equals("")) {
                String[] land_num = lblID.getText().split("L00");
                Integer type_id = LandTypeModel.getTypeId((String) cbLType.getValue());

                AddLandTypeFormController.landDetailList.add(new LandDetail(type_id, Integer.valueOf(land_num[1]), (String) cbLType.getValue()));

                try {
                    boolean isSaved = LandModel.update(new Land(
                            Integer.valueOf(land_num[1]), txtPlan.getText(), Double.valueOf(txtArea.getText())), AddLandTypeFormController.landDetailList, OwnershipFormController.ownerLists);

                    if (isSaved)
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
                    else
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                txtArea.setStyle("-fx-border-color:  #ef0d20; ");
                txtPlan.setStyle("-fx-border-color:  #ef0d20; ");
                new Alert(Alert.AlertType.ERROR, "Please Fill Compulsory Filed!").show();
            }
        }

    }

    public void btnOwnerOnAction(ActionEvent actionEvent){
        land_id=lblID.getText();
        OpenView.openView("ownershipForm");

        if(ownerList!=null) {
            for (int i = 0; i < ownerList.size(); i++) {
                index = i;
                OpenView.openView("ownershipForm");
            }
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("registrationForm",landRoot);
    }

    public void LandTypeOnAction(ActionEvent actionEvent) {
        land_id=lblID.getText();
        OpenView.openView("addLandTypeForm");

    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtPlan.clear();
        txtArea.clear();
        cbLType.setValue(null);
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            Detail detail = new Detail("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                boolean isSaved = DetailModel.save(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",landRoot);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",landRoot);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",landRoot);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",landRoot);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",landRoot);
    }

    public void txtAreaOnKeyReleased(KeyEvent keyEvent) {
        if (!txtArea.getText().matches("^[0-9.]*$")) {
            txtArea.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            lblArea.setText("This filed can only contain numeric values!");
        }
    }

    public void txtAreaOnKeyTyped(KeyEvent keyEvent) {
        if (txtArea.getText().matches("^^[0-9.]*$")) {
            txtArea.setStyle("-fx-border-color:  null; -fx-font-size: 16px;");
            lblArea.setText("");
        }
    }
}
