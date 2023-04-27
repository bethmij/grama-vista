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
import lk.ijse.dto.*;
import lk.ijse.dto.tm.DeadTM;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DeadModel;
import lk.ijse.model.DetailModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.IndividualFormController.civil1;

public class DeadManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox<String> cbDead;
    public TableView<DeadTM> tbl;
    public TableColumn colID;
    public TableColumn colCivil;
    public TableColumn colAge;
    public TableColumn colAction;

    public TableColumn colName;
    public TableColumn colDead;
    private ObservableList<DeadTM> obList = FXCollections.observableArrayList();
    public static Dead dead;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDeadId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCivil.setCellValueFactory(new PropertyValueFactory<>("Civil"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colDead.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }


    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<DeadDTO> deadDTOList  = DeadModel.searchAll();

            for (DeadDTO datalist : deadDTOList) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                DeadTM deadTM = new DeadTM(datalist.getDead_id(), datalist.getCivil_id(), datalist.getName(),datalist.getDate(),datalist.getAge(),btnDelete);
                obList.add(deadTM);
                tbl.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            DeadDTO deadDTO = DeadModel.search(cbDead.getValue());
            dead= new Dead(deadDTO.getDead_id(), deadDTO.getCivil_id(), deadDTO.getName(), deadDTO.getDate());

            OpenView.openView("deadPeopleForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        DeadDTO deadDTO = null;
        try {
                deadDTO = DeadModel.search(cbDead.getValue());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        DeadTM deadTM = new DeadTM(deadDTO.getDead_id(), deadDTO.getCivil_id(), deadDTO.getName(), deadDTO.getDate(), deadDTO.getAge(),btnDelete);
        obList.add(deadTM);
        tbl.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = DeadModel.dead((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        Detail detail = new Detail("Deletion", "bethmi", LocalTime.now(), LocalDate.now(), "Deleting dead_people id - "+colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));
                        try {
                            DetailModel.save(detail);
                        } catch (SQLException ex) {
                            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                        }
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove( tbl.getSelectionModel().getSelectedIndex());
                        tbl.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }



    private void loadDeadId() {
        List<String> id = null;
        try {
            id = DeadModel.loadDeadId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add(ids);
        }
        cbDead.setItems(dataList);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("reportForm",tblDivPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

    @FXML
    void lblLogoutOnAction(MouseEvent event) {

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
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",tblDivPane);
    }
}
