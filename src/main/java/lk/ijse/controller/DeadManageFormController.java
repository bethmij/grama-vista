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
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.DeadManageBO;
import lk.ijse.bo.custom.impl.DeadManageBOImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.DeadTM;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
    public static DeadDTO dead;
    DeadManageBO deadManageBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DEADMANAGEBO);

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
            List<DeadDTO> deadDTOList  = deadManageBO.searchAllDead();

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
            DeadDTO deadDTO = deadManageBO.searchDeath(cbDead.getValue());
            dead= new DeadDTO(deadDTO.getDead_id(), deadDTO.getCivil_id(), deadDTO.getName(), deadDTO.getDate(),null);

            OpenView.openView("deadPeopleForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        DeadDTO deadDTO = null;
        try {
                deadDTO = deadManageBO.searchDeath(cbDead.getValue());

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
                    boolean isDeleted = deadManageBO.deleteDead((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        DetailDTO detail = new DetailDTO("Deletion", "bethmi", LocalTime.now(), LocalDate.now(), "Deleting dead_people id - "+colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));
                        try {
                            deadManageBO.saveDetail(detail);
                        } catch (SQLException ex) {
                            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                        }
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove( tbl.getSelectionModel().getSelectedIndex());
                        tbl.refresh();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }



    private void loadDeadId() {
        List<String> id = null;
        try {
            id = deadManageBO.loadDeadId();
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
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                deadManageBO.saveDetail(detail);
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
