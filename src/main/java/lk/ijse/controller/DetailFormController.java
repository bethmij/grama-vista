package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.Detail;
import lk.ijse.dto.ElecCandidate;
import lk.ijse.dto.tm.DetailTM;
import lk.ijse.dto.tm.ElecCandidateTM;
import lk.ijse.model.DetailModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.VoteManageFormController.candidate;

public class DetailFormController implements Initializable {


    public TableView tbl2;
    public TableColumn colFunction;
    public TableColumn colUser;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colTime;
    public TableColumn colDate;
    public ComboBox cdDuration;
    public TableColumn colDescription;
    private ObservableList<DetailTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadDuration();
    }

    private void loadDuration() {
        String[] gender = new String[]{"Today","Last 2 Days","Last Week","All Time"};
        ObservableList<String> dataList = FXCollections.observableArrayList(gender);
        cdDuration.setItems(dataList);
    }

    private void setTable() {
    }

    private void setCellValueFactory() {
        colFunction.setCellValueFactory(new PropertyValueFactory<>("Function"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("User"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }


    public void cbDurationOnAction(ActionEvent actionEvent)  {
        List<Detail> details = new ArrayList<>();
        tbl2.getItems().clear();

            try {
                if(cdDuration.getValue().equals("Today"))
                    details = DetailModel.search(1);
                else if (cdDuration.getValue().equals("Last 2 Days"))
                    details = DetailModel.search(2);
                else if(cdDuration.getValue().equals("Last Week"))
                    details = DetailModel.search(7);
                else if(cdDuration.getValue().equals("All Time"))
                    details = DetailModel.searchAll();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        for (Detail detailList : details ) {

            DetailTM detailTM = new DetailTM(detailList.getFunction_name(), detailList.getUser(),
                    detailList.getTime(), detailList.getDate(),detailList.getDescription());
            obList.add(detailTM);
            tbl2.setItems(obList);
        }
    }
}
