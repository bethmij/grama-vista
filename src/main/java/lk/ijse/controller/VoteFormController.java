package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.model.*;
import lk.ijse.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.AddResidenceFormController.residenceList;
import static lk.ijse.controller.IndividualFormController.civil1;

public class VoteFormController implements Initializable {
    public ComboBox cmbEType;
    public TextField txtCandidateCount;
    public ComboBox cmbCandidate;
    public Button btnReset;
    public TextField txtElection;
    public AnchorPane Pane;
    public List<AddCandidate> addCandidateList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadElectionType();
        loadCandidate();
    }

    private void loadCandidate() {
        try {
            List<String> id = CandidateModel.loadElectionID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cmbCandidate.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadElectionType() {
        String[] gender = new String[]{"Local Authorities Election"};
        ObservableList<String> dataList = FXCollections.observableArrayList(gender);
        cmbEType.setItems(dataList);
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

    public void lblViewOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteResultForm",Pane);
    }

    public void lblAddOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteRegForm",Pane);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Vote vote = new Vote(txtElection.getText(), String.valueOf(cmbEType.getValue()),Integer.valueOf(txtCandidateCount.getText()));

        boolean isSaved = false;
        try {
            isSaved = VoteModel.save(vote,addCandidateList);
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (isSaved)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
        else
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
    }


    public void cmbCandidateOnAction(ActionEvent actionEvent) {
        int count = 0;

        for (int i = 0; i < addCandidateList.size(); i++) {
            if (cmbCandidate.getValue() != addCandidateList.get(i).getCandidate_id()) {
                count++;
            }
        }

        if(count== addCandidateList.size()) {
            addCandidateList.add(new AddCandidate(txtElection.getText(), String.valueOf(cmbCandidate.getValue())));
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Succesfully").show();
        }else
            new Alert(Alert.AlertType.ERROR,"Already Added!").show();

    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        txtElection.clear();
        cmbCandidate.setValue("");
        txtCandidateCount.clear();
        cmbEType.setValue("");

    }


}
