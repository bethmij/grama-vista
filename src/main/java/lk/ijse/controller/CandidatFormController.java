package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.CandidatBO;
import lk.ijse.bo.custom.impl.CandidatBOImpl;
import lk.ijse.dto.AddCandidateDTO;
import lk.ijse.dto.ElecCandidateDTO;
import lk.ijse.dto.tm.ElecCandidateTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.VoteManageFormController.candidate;
import static lk.ijse.controller.VoteManageFormController.voteDTO;

public class CandidatFormController implements Initializable {
    public TableView tbl2;
    public TableColumn colElection;
    public TableColumn colCandidate;
    public TableColumn colName;
    public TableColumn colPolitic;
    public TableColumn colAction;
    public ComboBox cbCandidat;
    private ObservableList<ElecCandidateTM> obList = FXCollections.observableArrayList();
    public List<AddCandidateDTO> addCandidateList = new ArrayList<>();
    CandidatBO candidatBO = new CandidatBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        setTable();
        loadCandidate();

    }

    private void setTable() {

        for (ElecCandidateDTO candidate : candidate ) {

            Button btnDelete = new Button("Delete");
            btnDelete.setCursor(Cursor.HAND);
            setDeleteBtnOnAction(btnDelete);

            ElecCandidateTM candidateTM = new ElecCandidateTM(candidate.getElection_id(), candidate.getCandidate_id(), candidate.getName(),
                    candidate.getPolitic(), btnDelete);
            obList.add(candidateTM);
            tbl2.setItems(obList);
        }
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = candidatBO.deleteVote((String) colElection.getCellData(tbl2.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove(tbl2.getSelectionModel().getSelectedIndex());
                        tbl2.refresh();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }
            }
        });
    }

    private void setCellValueFactory() {
        colElection.setCellValueFactory(new PropertyValueFactory<>("election_id"));
        colCandidate.setCellValueFactory(new PropertyValueFactory<>("candidate_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPolitic.setCellValueFactory(new PropertyValueFactory<>("politic"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void loadCandidate() {
        try {
            List<String> id = candidatBO.loadElectionId();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbCandidat.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void cbCandidateOnAction(ActionEvent actionEvent) throws SQLException {
        String name = null;
        try {
            name = candidatBO.getCandidateName(Integer.valueOf(String.valueOf(cbCandidat.getValue())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to add "+name+" to the Candidate List?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            int count = 0;

            for (int i = 0; i < addCandidateList.size(); i++) {
                if (cbCandidat.getValue() != addCandidateList.get(i).getCandidate_id()) {
                    count++;
                }
            }

            if(count== addCandidateList.size()) {
                addCandidateList.add(new AddCandidateDTO(voteDTO.getElection_id(), String.valueOf(cbCandidat.getValue())));
                boolean isAddCandidate = candidatBO.saveCandidate(addCandidateList);
                boolean isUpdated = candidatBO.updateVote(voteDTO.getElection_id());
                if(isAddCandidate && isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
                }else
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }else
                new Alert(Alert.AlertType.ERROR,"Already Added!").show();
        }
    }
}
