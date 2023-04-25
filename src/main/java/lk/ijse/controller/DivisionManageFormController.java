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
import lk.ijse.dto.Division;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.dto.tm.DivisionTM;
import lk.ijse.model.DivisionModel;
import lk.ijse.model.ResidenceModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DivisionManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbDivision;
    public Label lblDivision;
    public TableView tblDivision;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colSecretary;
    public TableColumn colAdmin;
    public TableColumn colPopulation;
    public TableColumn colLand;
    public TableColumn colAction;
    public Label lblName;
    private ObservableList<DivisionTM> obList = FXCollections.observableArrayList();
    public static Division division;
    public static String division_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSecretary.setCellValueFactory(new PropertyValueFactory<>("Secretary"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("Admin"));
        colPopulation.setCellValueFactory(new PropertyValueFactory<>("Population"));
        colLand.setCellValueFactory(new PropertyValueFactory<>("Land"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("Delete"));

    }

    private void loadDivisionID() {
        try {
            List<String> id = DivisionModel.loadDivisionID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbDivision.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {

        try {
            List<DivisionDTO> divisionList  = DivisionModel.searchAll();

            for (DivisionDTO datalist : divisionList) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                DivisionTM divisionTM = new DivisionTM(datalist.getDivision_id(), datalist.getName(), datalist.getDiv_Secretariat(),
                        datalist.getAdmin_officer(), datalist.getPopulation(), datalist.getLand_area(), btnDelete);
                obList.add(divisionTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {
            DivisionDTO divisionDTO = DivisionModel.search((String) cbDivision.getValue());
            division = new Division(divisionDTO.getDivision_id(),divisionDTO.getName(),divisionDTO.getDiv_Secretariat(),
                                        divisionDTO.getAdmin_officer(),divisionDTO.getLand_area());
            OpenView.openView("divisionRegistrationForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        DivisionDTO divisionDTO = DivisionModel.search((String) cbDivision.getValue());
        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        DivisionTM divisionTM = new DivisionTM(divisionDTO.getDivision_id(), divisionDTO.getName(), divisionDTO.getDiv_Secretariat(),
                divisionDTO.getAdmin_officer(), divisionDTO.getPopulation(), divisionDTO.getLand_area(), btnDelete);
        obList.add(divisionTM);
        tblDivision.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete) {

        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = DivisionModel.dead((String) colID.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));
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


    public void cbDivisionOnAction(MouseEvent mouseEvent) {
        try {
            lblName.setText(DivisionModel.getName((String) cbDivision.getValue()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void lblManageOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    @FXML
    void lblRegLogOnAction(MouseEvent event) {
        OpenView.openView("loginForm",tblDivPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",tblDivPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }
}
