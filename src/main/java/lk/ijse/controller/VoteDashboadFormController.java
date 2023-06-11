package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
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

public class VoteDashboadFormController {
    public AnchorPane Pane;

    public void btnAddOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteRegForm",Pane);
    }

    public void btnVListOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElectionReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnVoteOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }

    public void btnResultOnAction(ActionEvent actionEvent) {
        LocalTime start = LocalTime.parse("10:00");
        LocalDate date = LocalDate.parse("2023-06-11");

        if(date.compareTo(LocalDate.now())==0 && LocalTime.now().isAfter(start) ) {
            OpenView.openView("voteResultForm",Pane);
        }else{
            new Alert(Alert.AlertType.ERROR, "This only eligible on "+date+" after "+start).show();
        }

    }
}
