package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.model.CivilModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VoteLoginFormController implements Initializable {
    public ComboBox cmbElection;
    public ComboBox cmbCivil;
    public Label lblName;
    public AnchorPane Pane;
    public static Integer civilID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCivilID();
    }

    private void loadCivilID() {
        List<String> id = null;
        try {
            id = CivilModel.loadElectCivilId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add("C00"+ids);
        }
        cmbCivil.setItems(dataList);
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



    public void cmbElectionOnAction(ActionEvent actionEvent) {

        String id = (String) cmbCivil.getValue();
        String[] civil_id = id.split("C00");
        civilID = Integer.valueOf(civil_id[1]);

        if(cmbElection.getValue().equals("E001")){
            OpenView.openView("voteForm",Pane);
        }
    }

    public void cmbCivilOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCivil.getValue();
        String[] civil_id = id.split("C00");
        List<String> election_id = new ArrayList<>();
        List<String> election_id_list = new ArrayList<>();

        try {
            lblName.setText(CivilModel.searchById(civil_id[1]));
            election_id = VoteModel.getElecID(String.valueOf(civil_id[1]));
            election_id_list = VoteModel.getElecID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        for (int i=0; i<election_id_list.size(); i++){
            for(int j=0; j<election_id.size(); j++){
                if(election_id_list.get(i).equals(election_id.get(j))){
                    election_id_list.remove(i);
                }
            }
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : election_id_list) {
            dataList.add(ids);
        }
        cmbElection.setItems(dataList);
    }


}
