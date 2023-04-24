package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class PregReportFormController {


    public void btnFeedOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/FeedReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPregOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/PregReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
