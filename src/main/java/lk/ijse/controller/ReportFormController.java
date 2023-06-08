package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.ReportBO;
import lk.ijse.bo.custom.impl.ReportBOImpl;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dao.custom.impl.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class ReportFormController {
    public AnchorPane tblDivPane;
    ReportBO reportBO = new ReportBOImpl();

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                reportBO.saveDetail(detail);
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

    public void btnSamuOnAction(ActionEvent actionEvent) {
        OpenView.openView("samurdhiForm",tblDivPane);
    }

    public void btnElectionOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElectionReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnChildOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ChildReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPregOnAction(ActionEvent actionEvent) {
        OpenView.openView("pregReportForm");
    }

    public void btnDisableOnAction(ActionEvent actionEvent) {

        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/DisableReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnElderOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElderReprt.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
