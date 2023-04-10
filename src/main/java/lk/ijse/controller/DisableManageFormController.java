package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Dead;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.Disable;
import lk.ijse.dto.DisableDTO;
import lk.ijse.dto.tm.DeadTM;
import lk.ijse.dto.tm.DisableTM;
import lk.ijse.model.DeadModel;
import lk.ijse.model.DisableModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DisableManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbReg;
    public TableView tblDivision;
    public TableColumn colID;
    public TableColumn colCivil;
    public TableColumn colName;
    public TableColumn colDisable;
    public TableColumn colDesc;
    public TableColumn colAction;
    private ObservableList<DisableTM> obList = FXCollections.observableArrayList();
    public static Disable disable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDisableId();
        setCellValueFactory();
    }

    private void loadDisableId() {
        List<String> id = null;
        try {
            id = DisableModel.loadDisableId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add(ids);
        }
        cbReg.setItems(dataList);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCivil.setCellValueFactory(new PropertyValueFactory<>("Civil"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colDisable.setCellValueFactory(new PropertyValueFactory<>("Disable"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<DisableDTO> disableDTO  = DisableModel.searchAll();

            for (DisableDTO datalist : disableDTO) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                DisableTM disableTM = new DisableTM(datalist.getID(), datalist.getCivil(), datalist.getName(), datalist.getDisable(), datalist.getDesc(), btnDelete);
                obList.add(disableTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            DisableDTO disableDTO = DisableModel.search((String) cbReg.getValue());
            disable= new Disable(disableDTO.getID(),disableDTO.getCivil(), disableDTO.getName(), disableDTO.getDisable(), disableDTO.getDesc());

            OpenView.openView("disableRegistrationForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        DisableDTO disableDTO = null;
        try {
            disableDTO = DisableModel.search((String) cbReg.getValue());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        DisableTM disableTM = new DisableTM(disableDTO.getID(), disableDTO.getCivil(), disableDTO.getName(), disableDTO.getDisable(), disableDTO.getDesc(), btnDelete);
        obList.add(disableTM);
        tblDivision.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = DisableModel.delete((String)cbReg.getValue());

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove( tblDivision.getSelectionModel().getSelectedIndex());
                        tblDivision.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }

    @FXML
    void lblLogoutOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    @FXML
    void lblMAnageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",tblDivPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {

    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {

    }

}
