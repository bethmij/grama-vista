package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.model.VoteModel;
import lk.ijse.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.VoteLoginFormController.civilID;

public class VoteeFormController implements Initializable {
    public AnchorPane Pane;
    public Label lblDate;
    public CheckBox cb1;
    public CheckBox cb2;
    public CheckBox cb3;
    public Label lblElection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderDate();
    }

    private void setOrderDate() {
      lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm", Pane);
        }
    }

    public void lblVListOnAction(MouseEvent mouseEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElectionReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            e.printStackTrace();
        }
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }



    public void cb1OnAction(ActionEvent actionEvent) {
        if(cb2.isSelected() || cb3.isSelected()){
            cb1.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void cb2OnAction(ActionEvent actionEvent) {
        if(cb1.isSelected() || cb3.isSelected()){
            cb2.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void cb3OnAction(ActionEvent actionEvent) {
        if(cb1.isSelected() || cb2.isSelected()){
            cb3.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void btnVoteOnAction(ActionEvent actionEvent) {

        if(cb1.isSelected() || cb2.isSelected() || cb3.isSelected()){
            Integer candidate_id = null;

            if(cb1.isSelected())
                candidate_id = 1;
            else if (cb2.isSelected())
                candidate_id = 2;
            else if(cb3.isSelected())
                candidate_id = 3;

            try {
                boolean isSaved = VoteModel.saveVote(lblElection.getText(), candidate_id ,civilID);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Your vote has been recorded!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }else
            new Alert(Alert.AlertType.ERROR,"Please select a candidate!").show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }
}
