package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

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
            e.printStackTrace();
        }
    }

    public void btnVoteOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }

    public void btnResultOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteResultForm",Pane);
    }
}
