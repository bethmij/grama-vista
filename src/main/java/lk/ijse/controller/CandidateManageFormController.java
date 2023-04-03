package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.Candidate;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.Division;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.dto.tm.CandidateTM;
import lk.ijse.dto.tm.DivisionTM;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CandidateManageFormController implements Initializable {

    public Label lblDivision;
    public TableView tblDivision;
    public TableColumn colElection;
    public TableColumn colImage;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colDivision;
    public TableColumn colAction;
    public AnchorPane tblDivPane;
    public ComboBox cmbID;
    private ObservableList<CandidateTM> obList = FXCollections.observableArrayList();
    public static Candidate candidate;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colElection.setCellValueFactory(new PropertyValueFactory<>("Election"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void loadDivisionID() {
        try {
            List<String> id = CandidateModel.loadElectionID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cmbID.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        

        try {
            List<CandidateDTO> candidateList  = CandidateModel.searchAll();

            for (CandidateDTO datalist : candidateList) {
                Button btnView = new Button("View more");
                btnView.setCursor(Cursor.HAND);
                setViewBtnOnAction(btnView);

                CandidateTM candidateTM = new CandidateTM(datalist.getElection(), datalist.getImage(), datalist.getName(),
                        datalist.getNIC(), datalist.getDivision(), btnView);
                obList.add(candidateTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            CandidateDTO candidateDTO = CandidateModel.search((String) cmbID.getValue());
            candidate = new Candidate(candidateDTO.getElection(), candidateDTO.getDivision(), candidateDTO.getNIC(),
                    candidateDTO.getName(),candidateDTO.getPolitic(),candidateDTO.getAddress(), candidateDTO.getContact());
            OpenView.openView("candidateForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        CandidateDTO candidateDTO = null;
        try {
             candidateDTO = CandidateModel.search((String) cmbID.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*ImageView imageView = new ImageView();
        imageView.setImage((Image) candidateDTO.getImage());*/
        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);



        CandidateTM candidateTM = new CandidateTM(candidateDTO.getElection(), candidateDTO.getImage(), candidateDTO.getName(),
                                                 candidateDTO.getNIC(), candidateDTO.getDivision(), btnView);
        obList.add(candidateTM);
        tblDivision.setItems(obList);
    }

    private void setViewBtnOnAction(Button btnView) {
        /*btnView.setOnAction((e) -> {
           OpenView.openView("candidateManageForm2");

           new CandidateManageForm2Controller().setTbl2(candidateDTO);
        });*/
    }


    public void cmbIDOnAction(ActionEvent actionEvent) {
        try {
            lblDivision.setText(CandidateModel.getName((String) cmbID.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
