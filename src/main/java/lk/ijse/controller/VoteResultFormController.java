package lk.ijse.controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.VoteResultBO;
import lk.ijse.bo.custom.impl.VoteResultBOImpl;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dao.custom.impl.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class VoteResultFormController implements Initializable {
    public BarChart <String,Integer> chart;
    public AnchorPane Pane;
    public Label lblPosition;
    public Label lblCandidate;
    public Label lblVoters;
    public Label lblVoted;
    public Label lblVote1;
    public Label lblVote2;
    public Label lblVote3;
    public Label lblWinner;
    VoteResultBO voteResultBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VOTERESULTBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            lblCandidate.setText(String.valueOf(voteResultBO.getCandidateCount()));
            lblPosition.setText(String.valueOf(voteResultBO.getCandidateCount()-2));
            lblVoters.setText(String.valueOf(voteResultBO.getCivilCount() ));
            lblVoted.setText(String.valueOf(voteResultBO.getVoteCount()));
            lblVote1.setText(String.valueOf(voteResultBO.getVote(1)));
            lblVote2.setText(String.valueOf(voteResultBO.getVote(2)));
            lblVote3.setText(String.valueOf(voteResultBO.getVote(3)));
            getWinner();
            getChart();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void getChart() {
        XYChart.Series series1 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data("1",Integer.valueOf(lblVote1.getText())));
        series1.getData().add(new XYChart.Data("2",Integer.valueOf(lblVote2.getText())));
        series1.getData().add(new XYChart.Data("3",Integer.valueOf(lblVote3.getText())));

        chart.getData().addAll(series1);
    }

    private void getWinner() {
        try {
            Integer maxVote = Math. max(voteResultBO.getVote(1), Math. max(voteResultBO.getVote(2), voteResultBO.getVote(3)));
            for (int i=0; i<3; i++){
                if(maxVote== voteResultBO.getVote(i)){
                    String name = voteResultBO.getWinnerName(i);
                    lblWinner.setText(name);
                }
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                voteResultBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm", Pane);
        }
    }

    public void lblVListOnAction(MouseEvent mouseEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElectionReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }

    public void lblViewOnAction(MouseEvent mouseEvent) {
        LocalTime start = LocalTime.parse("10:00");
        LocalDate date = LocalDate.parse("2023-06-08");

        if(date.compareTo(LocalDate.now())==0 && LocalTime.now().isAfter(start) ) {
            OpenView.openView("voteResultForm",Pane);
        }else{
            new Alert(Alert.AlertType.ERROR, "This only eligible on "+date+" after "+start).show();
        }
    }

    public void lblAddOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteRegForm",Pane);
    }


    public void lblEditOnAction(MouseEvent mouseEvent) {
        OpenView.openView("VoteManageForm",Pane);
    }
}
