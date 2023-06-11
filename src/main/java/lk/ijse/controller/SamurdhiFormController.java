package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.SamurdhiBO;
import lk.ijse.bo.custom.impl.SamurdhiBOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;
import lk.ijse.dto.tm.SamurdhiTM;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.SamurdhiRegFormController.samurdhiTM;

public class SamurdhiFormController implements Initializable {
    public TableColumn colHome;
    public TableColumn colHouse;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colMember;
    public TableView tblDivision;
    public AnchorPane tblDivPane;
    public TableColumn colCivil;
    private ObservableList<SamurdhiTM> obList = FXCollections.observableArrayList();
    SamurdhiBO samurdhiBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SAMURDHIBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colHome.setCellValueFactory(new PropertyValueFactory<>("home_id"));
        colCivil.setCellValueFactory(new PropertyValueFactory<>("civil_id"));
        colHouse.setCellValueFactory(new PropertyValueFactory<>("houseHolder"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMember.setCellValueFactory(new PropertyValueFactory<>("member"));
    }

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                samurdhiBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    public void lblManageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void lblReportOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reportForm",tblDivPane);
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

    public void lblRegOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("reportForm",tblDivPane);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        OpenView.openView("samurdhiRegForm");
        /*SamurdhiTM samurdhiTMList = new SamurdhiTM(samurdhiTM.getHome_id(), samurdhiTM.getCivil_id(), samurdhiTM.getHouseHolder(),
                                                    samurdhiTM.getNIC(), samurdhiTM.getAddress(), samurdhiTM.getMember());
        obList.add(samurdhiTMList);
        tblDivision.setItems(obList);*/
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        List<SamurdhiTM> samurdhiTMList = new ArrayList<>();
        for (int i = 0; i < tblDivision.getItems().size(); i++) {
            SamurdhiTM samurdhiTM = obList.get(i);
            samurdhiTMList.add(samurdhiTM);
        }

        try {
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(samurdhiTMList);
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/SamurdhiReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null,jr);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUploadOnAction(ActionEvent actionEvent) {

        try {
            List<String> residenceDTO = samurdhiBO.loadResidenceId();
            for (int i = 0; i < residenceDTO.size(); i++) {
                List<CivilDTO> civilDetail = samurdhiBO. getCivilDetail(residenceDTO.get(i));
                int salary_count = 0; //to count salary count if it's below the rate

                for (int j=0; j< civilDetail.size(); j++){
                    if (civilDetail.get(j).getSalary()<50000){
                        salary_count++;
                    }
                }
                if (salary_count == civilDetail.size()){
                    ResidenceDTO residenceDetail = samurdhiBO.searchResidence(residenceDTO.get(i));
                    String civil_id = null;
                    String name = null;
                    String nic = null;

                    for (int j=0; j<civilDetail.size(); j++){
                        if(civilDetail.get(j).getRelation().equals("House Holder")){
                            civil_id=civilDetail.get(j).getID();
                            name=civilDetail.get(j).getName();
                            nic=civilDetail.get(j).getNic();
                        }
                    }
                    SamurdhiTM samurdhiTM = new SamurdhiTM(residenceDetail.getID(),civil_id,name,nic, residenceDetail.getAddress(),residenceDetail.getMember() );
                    obList.add(samurdhiTM);
                    tblDivision.setItems(obList);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        if (samurdhiTM!=null) {
            SamurdhiTM samurdhiTMList = new SamurdhiTM(samurdhiTM.getHome_id(), samurdhiTM.getCivil_id(), samurdhiTM.getHouseHolder(),
                    samurdhiTM.getNIC(), samurdhiTM.getAddress(), samurdhiTM.getMember());
            obList.add(samurdhiTMList);
            tblDivision.setItems(obList);
        }
    }
}
