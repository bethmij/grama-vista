package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.VoteManageBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.VoteTM;
import lk.ijse.dao.custom.impl.util.OpenView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VoteManageFormController implements Initializable {
    public ChoiceBox cbElection;
    public TableView tbl;
    public TableColumn colID;
    public TableColumn colType;
    public TableColumn colDate;
    public TableColumn colCount;
    public TableColumn colCandidate;
    public TableColumn colAction;
    public AnchorPane Pane;
    private ObservableList<VoteTM> obList = FXCollections.observableArrayList();
    public static VoteDTO voteDTO;
    public static List<ElecCandidateDTO> candidate = new ArrayList<>();
    VoteManageBO voteManageBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VOTEMANAGEBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadElectionId();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("election_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("election_type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("candidate_count"));
        colCandidate.setCellValueFactory(new PropertyValueFactory<>("view"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));

    }

    private void loadElectionId() {
        try {
            List<String> id = voteManageBO.getElectID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbElection.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<VoteDTO> voteDTOList = voteManageBO.searchAllVote();

            for (VoteDTO datalist : voteDTOList) {

                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                Button btnView = new Button("View more");
                btnView.setCursor(Cursor.HAND);
                setViewBtnOnAction(btnView);

                VoteTM voteTM = new VoteTM(datalist.getElection_id(), datalist.getElection_type(), datalist.getDate(),
                        datalist.getCandidate_count(), btnView,btnDelete);
                obList.add(voteTM);
                tbl.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            voteDTO = voteManageBO.searchVote((String)cbElection.getValue());

            OpenView.openView("voteRegForm");
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            voteDTO = voteManageBO.searchVote((String)cbElection.getValue());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);

        VoteTM voteTM = new VoteTM(voteDTO.getElection_id(), voteDTO.getElection_type(), voteDTO.getDate(), voteDTO.getCandidate_count(),
                btnView,btnDelete);
        obList.add(voteTM);
        tbl.setItems(obList);
    }

    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            try {
                candidate = voteManageBO.searchCandidate((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
            OpenView.openView("CandidateForm");
        });
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = voteManageBO.deleteVote((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove(tbl.getSelectionModel().getSelectedIndex());
                        tbl.refresh();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                voteManageBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm", Pane);
        }
    }

    public void lblVListOnAction(MouseEvent mouseEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report/ElectionReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e ) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }

    public void lblViewOnAction(MouseEvent mouseEvent) {
        LocalTime start = LocalTime.parse("10:00");
        LocalDate date = LocalDate.parse("2023-04-30");

        if(date.compareTo(LocalDate.now())==0 && LocalTime.now().isAfter(start) ) {
            OpenView.openView("voteResultForm",Pane);
        }else{
            new Alert(Alert.AlertType.ERROR, "This only eligible on "+date+" after "+start).show();
        }
    }

    public void lblAddOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteRegForm",Pane);
    }

    public void lblEditOnAction(MouseEvent mouseEvent) {
        OpenView.openView("VoteManageForm",Pane);
    }


}
