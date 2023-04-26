package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.Detail;
import lk.ijse.dto.Vote;
import lk.ijse.model.CivilModel;
import lk.ijse.model.DetailModel;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class VoteLoginFormController implements Initializable {
    public ComboBox cmbElection;
    public Label lblName;
    public AnchorPane Pane;
    public static Integer civilID;
    public TextField txtNIC;
    public Label lblCivil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbElection.setDisable(true);
    }


    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            Detail detail = new Detail("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                boolean isSaved = DetailModel.save(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm", Pane);
        }
    }


    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }



    public void cmbElectionOnAction(ActionEvent actionEvent) throws ParseException {


        //civilID = Integer.valueOf(ci);

        LocalTime start = LocalTime.parse("08:00");
        LocalTime end = LocalTime.parse("22:00");


        Vote vote = null;
        try {
            vote = VoteModel.search((String) cmbElection.getValue());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        if(cmbElection.getValue().equals("E001")){
            if(vote.getDate().compareTo(LocalDate.now())<0 && !LocalTime.now().isBefore(start) && !LocalTime.now().isAfter(end)) {
                OpenView.openView("voteForm", Pane);
            }else
                new Alert(Alert.AlertType.ERROR, "This only eligible on "+vote.getDate()+" during "+start+"-"+end).show();
        }
    }




    public void txtNICOnAction(ActionEvent actionEvent) {

        List<String> election_id = new ArrayList<>();
        List<String> election_id_list = new ArrayList<>();

        try {
            CivilDTO civilDTO = CivilModel.searchbyNIC(txtNIC.getText());
            cmbElection.setDisable(false);
            lblCivil.setText(civilDTO.getID());
            lblName.setText(civilDTO.getName());
            election_id = VoteModel.getElecID(civilDTO.getID());
            election_id_list = VoteModel.getElecID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC!").show();
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
