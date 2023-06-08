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
import lk.ijse.bo.custom.DisableManageBO;
import lk.ijse.bo.custom.impl.DisableManageBOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;
import lk.ijse.dto.tm.DisableTM;
import lk.ijse.dao.custom.impl.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    public static DisableDTO disable;
    DisableManageBO disableManageBO = new DisableManageBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDisableId();
        setCellValueFactory();
    }

    private void loadDisableId() {
        List<String> id = null;
        try {
            id = disableManageBO.loadDisableId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
            List<DisableDTO> disableDTO  = disableManageBO.searchAllDisable();

            for (DisableDTO datalist : disableDTO) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                DisableTM disableTM = new DisableTM(datalist.getId(), datalist.getCivil(), datalist.getName(), datalist.getDisable(), datalist.getDesc(), btnDelete);
                obList.add(disableTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            disable = disableManageBO.searchDisable((String) cbReg.getValue());
            
            OpenView.openView("disableRegistrationForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        DisableDTO disableDTO = null;
        try {
            disableDTO = disableManageBO.searchDisable((String) cbReg.getValue());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        DisableTM disableTM = new DisableTM(disableDTO.getId(), disableDTO.getCivil(), disableDTO.getName(), disableDTO.getDisable(), disableDTO.getDesc(), btnDelete);
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
                    boolean isDeleted = disableManageBO.deleteDisable((String) colID.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));

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
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                disableManageBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
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
        OpenView.openView("reportForm",tblDivPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

    public void btnReportOnAction(ActionEvent actionEvent) {

        List<DisableDTO> disableDTOList = new ArrayList<>();
        for (int i = 0; i < tblDivision.getItems().size(); i++) {
            DisableTM disableTM = obList.get(i);

            DisableDTO dto = new DisableDTO(
                    disableTM.getId(), disableTM.getCivil(), disableTM.getName(), disableTM.getDisable(), disableTM.getDesc());
            disableDTOList.add(dto);
        }

        try {
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(disableDTOList);
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/report.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null,jr);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
