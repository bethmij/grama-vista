package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.AddCandidate;
import lk.ijse.dto.ElecCandidate;
import lk.ijse.dto.tm.ElecCandidateTM;
import lk.ijse.model.AddCandidateModel;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.VoteModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.VoteManageFormController.candidate;
import static lk.ijse.controller.VoteManageFormController.vote;

public class CandidatFormController implements Initializable {
    public TableView tbl2;
    public TableColumn colElection;
    public TableColumn colCandidate;
    public TableColumn colName;
    public TableColumn colPolitic;
    public TableColumn colAction;
    public ComboBox cbCandidat;
    private ObservableList<ElecCandidateTM> obList = FXCollections.observableArrayList();
    public List<AddCandidate> addCandidateList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        setTable();
        loadCandidate();

    }

    private void setTable() {

        for (ElecCandidate candidate : candidate ) {

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
                    boolean isDeleted = VoteModel.deleteCandidate((String) colElection.getCellData(tbl2.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove(tbl2.getSelectionModel().getSelectedIndex());
                        tbl2.refresh();
                    }
                } catch (SQLException ex) {
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
            List<String> id = CandidateModel.loadElectionID();
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
            name = VoteModel.getName(Integer.valueOf(String.valueOf(cbCandidat.getValue())));
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
                addCandidateList.add(new AddCandidate(vote.getElection_id(), String.valueOf(cbCandidat.getValue())));
                boolean isAddCandidate = AddCandidateModel.save(addCandidateList);
                boolean isUpdated = VoteModel.updateCount(vote.getElection_id());
                if(isAddCandidate && isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
                }else
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }else
                new Alert(Alert.AlertType.ERROR,"Already Added!").show();
        }
    }
}
