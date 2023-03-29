package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.model.LandModel;
import lk.ijse.util.OpenView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LandFormController implements Initializable {
    public AnchorPane landRoot;
    public TextField txtPlan;
    public TextField txtArea;
    public ChoiceBox cbLType;
    public Label lblID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandType();
        generateLandId();
    }

    private void generateLandId() {
        try {
            String id = LandModel.getNextLandId();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadLandType() {
        String[] type = new String[]{"Government","Non Government","Cultivated", "Uncultivated"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbLType.setItems(dataList);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnOwnerOnAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(OpenView.class.getResource("/view/ownershipForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Ownership Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("civilRegistrationForm",landRoot);
    }



}
