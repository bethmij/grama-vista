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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.Candidate2TM;
import lk.ijse.dto.tm.CandidateTM;
import lk.ijse.dto.tm.DivisionTM;
import lk.ijse.model.CandidateModel;
import lk.ijse.model.DetailModel;
import lk.ijse.model.DivisionModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public static CandidateTM candidateTM;
    public static CandidateDTO candidateDetail;
    public static String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDivisionID();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colElection.setCellValueFactory(new PropertyValueFactory<>("Election"));
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
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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

                candidateTM = new CandidateTM(datalist.getElection(),datalist.getName(),
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
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);

        candidateTM = new CandidateTM(candidateDTO.getElection(), candidateDTO.getName(),
                                                 candidateDTO.getNIC(), candidateDTO.getDivision(), btnView);




        obList.add(candidateTM);
        tblDivision.setItems(obList);
    }


    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {

            try {
                CandidateDTO candidateDTO = CandidateModel.search((String) colElection.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));
                id = (String) colElection.getCellData(tblDivision.getSelectionModel().getSelectedIndex());
                candidateDetail = new CandidateDTO(candidateDTO.getElection(),candidateDTO.getImage(),candidateDTO.getName(),candidateDTO.getNIC(),
                        candidateDTO.getDivision(),candidateDTO.getAddress(),candidateDTO.getContact(),candidateDTO.getPolitic());
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR,ex.getMessage()).show();
            }
            OpenView.openView("candidateViewForm");


        });
    }



    public void cmbIDOnAction(ActionEvent actionEvent) {
        try {
            lblDivision.setText(CandidateModel.getName((String) cmbID.getValue()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    public void lblManageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void lblReportOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reportForm",tblDivPane);
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

    public void lblRegOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",tblDivPane);
    }
}
