package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.LandTM;
import lk.ijse.dto.tm.MaternityTM;
import lk.ijse.model.LandModel;
import lk.ijse.model.LandTypeModel;
import lk.ijse.model.MaternityModel;
import lk.ijse.model.OwnerModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LandManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbLand;
    public TableView tblDivision;
    public TableColumn colID;
    public TableColumn colPlan;
    public TableColumn colArea;
    public TableColumn colGov;
    public TableColumn colCultivate;;
    public TableColumn colAction;
    private ObservableList<LandTM> obList = FXCollections.observableArrayList();
    public static Land land = null;
    public static List<LandDetail> landDetails =null;
    public static List<Owner> ownerList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandId();
        setCellValueFactory();
    }

    private void loadLandId() {
        try {
            List<String> id = LandModel.loadLandID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbLand.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colPlan.setCellValueFactory(new PropertyValueFactory<>("Plan"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("Area"));
        colGov.setCellValueFactory(new PropertyValueFactory<>("Gov"));
        colCultivate.setCellValueFactory(new PropertyValueFactory<>("Cultivate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<Land> landList =LandModel.searchAll();
            List<LandDetail> landDetail = LandTypeModel.searchAllLandDetail();
            List<Owner> ownerLists = OwnerModel.searchAllOwner();

            for (Land datalist : landList) {
                Button btnView = new Button("View more");
                btnView.setCursor(Cursor.HAND);
                setViewBtnOnAction(btnView);

                String gov = null;
                String cultivate = null;

                for (int i =0; i<landDetail.size(); i++) {

                    if (landDetail.get(i).getLand_type().equals("Government"))
                        gov = "Government";
                    else if (landDetail.get(i).getLand_type().equals("Non Government"))
                        gov = "Non Government";
                    else if (landDetail.get(i).getLand_type().equals("Cultivated"))
                        cultivate = "Cultivated";
                    else if (landDetail.get(i).getLand_type().equals("Uncultivated"))
                        cultivate = "Uncultivated";
                }

                LandTM landTM = new LandTM(String.valueOf(datalist.getLand_id()),datalist.getPlan_num(), datalist.getL_area(),gov,cultivate,btnView);
                obList.add(landTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            land =LandModel.search((String)cbLand.getValue());
            landDetails = LandTypeModel.searchLandDetail((String) cbLand.getValue());
            ownerList = OwnerModel.searchOwner((String) cbLand.getValue());

            OpenView.openView("landForm");

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            land =LandModel.search((String)cbLand.getValue());
            landDetails = LandTypeModel.searchLandDetail((String) cbLand.getValue());
            ownerList = OwnerModel.searchOwner((String) cbLand.getValue());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);

        String gov = null;
        String cultivate = null;
        for (int i =0; i<landDetails.size(); i++) {
            if (landDetails.get(i).getLand_type().equals("Government"))
                gov = "Government";
            else if (landDetails.get(i).getLand_type().equals("Non Government"))
                gov = "Non Government";
            else if (landDetails.get(i).getLand_type().equals("Cultivated"))
                cultivate = "Cultivated";
            else if (landDetails.get(i).getLand_type().equals("Uncultivated"))
                cultivate = "Uncultivated";

        }

        LandTM landTM = new LandTM(String.valueOf(land.getLand_id()),land.getPlan_num(), land.getL_area(),gov,cultivate,btnView);
        obList.add(landTM);
        tblDivision.setItems(obList);
    }

    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            OpenView.openView("candidateViewForm");
        });
    }

}