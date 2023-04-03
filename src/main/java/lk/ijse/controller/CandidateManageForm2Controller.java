package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.tm.Candidate2TM;
import lk.ijse.dto.tm.CandidateTM;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DivisionModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CandidateManageForm2Controller implements Initializable {
    public TableView tbl2;
    public TableColumn colElection;
    public TableColumn colAge;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colPolitic;
    public TableColumn colAction;
    private ObservableList<Candidate2TM> obList = FXCollections.observableArrayList();
    private CandidateDTO candidateDTO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();


    }

    private void setCellValueFactory() {
        colElection.setCellValueFactory(new PropertyValueFactory<>("Election"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colPolitic.setCellValueFactory(new PropertyValueFactory<>("Politic"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void setTbl2(CandidateDTO candidateDTO){
        setTable(candidateDTO);
    }


    public  void setTable(CandidateDTO candidateDTO){


        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction( btnDelete, candidateDTO);

        Candidate2TM candidate2TM = new Candidate2TM(candidateDTO.getElection(), candidateDTO.getAddress(), candidateDTO.getContact(),
                candidateDTO.getPolitic(), btnDelete);
        obList.add(candidate2TM);
        tbl2.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete, CandidateDTO candidateDTO) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = DivisionModel.dead(candidateDTO.getElection());
                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove( tbl2.getSelectionModel().getSelectedIndex());
                        tbl2.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }



}
