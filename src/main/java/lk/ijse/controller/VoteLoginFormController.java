package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.VoteLoginBO;
import lk.ijse.bo.custom.impl.VoteLoginBOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.VoteDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
    VoteLoginBO voteLoginBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VOTELOGINBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbElection.setDisable(true);
    }


    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                voteLoginBO.saveDetail(detail);
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


        civilID = Integer.valueOf(lblCivil.getText());

        LocalTime start = LocalTime.parse("08:00");
        LocalTime end = LocalTime.parse("20:00");


        VoteDTO voteDTO = null;
        try {
            voteDTO = voteLoginBO.searchVote((String) cmbElection.getValue());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        if(cmbElection.getValue().equals("E001")){
            if(voteDTO.getDate().compareTo(LocalDate.now())<0 && !LocalTime.now().isBefore(start) && !LocalTime.now().isAfter(end)) {
                OpenView.openView("voteForm", Pane);
            }else
                new Alert(Alert.AlertType.ERROR, "This only eligible on "+ voteDTO.getDate()+" during "+start+"-"+end).show();
        }
    }




    public void txtNICOnAction(ActionEvent actionEvent) {

        List<String> election_id = new ArrayList<>();
        List<String> election_id_list = new ArrayList<>();

        try {
            CivilDTO civilDTO = voteLoginBO.searchByCivilNIC(txtNIC.getText());
            cmbElection.setDisable(false);
            lblCivil.setText(civilDTO.getID());
            lblName.setText(civilDTO.getName());
            election_id = voteLoginBO.getElectionIDbyID(civilDTO.getID());
            election_id_list = voteLoginBO.getElectionID();
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
