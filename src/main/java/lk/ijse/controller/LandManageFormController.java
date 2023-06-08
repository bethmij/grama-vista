package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.LandManageBO;
import lk.ijse.bo.custom.impl.LandManageBOImpl;
import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;
import lk.ijse.dto.tm.LandTM;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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
    public static LandDTO land = null;
    public static List<LandDetailDTO> landDetails =null;
    public static List<CoOwnerDTO> coOwnerList = null;
    LandManageBO landManageBO = new LandManageBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandId();
        setCellValueFactory();
    }

    private void loadLandId() {
        try {
            List<String> id = landManageBO.loadLandId();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbLand.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
            List<LandDTO> landList = landManageBO.searchAllLand();
            List<LandDetailDTO> landDetail = landManageBO.searchAllLandDetail() ;
            List<CoOwnerDTO> coOwnerLists = landManageBO.searchAllOwners();

            for (LandDTO datalist : landList) {
                Button btnDelete = new Button("Remove");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                String gov = null;
                String cultivate = null;


                    for (LandDetailDTO landDetailDTO : landDetail) {
                        if (datalist.getLand_id().equals(landDetailDTO.getLand_num())) {
                            switch (landDetailDTO.getLand_type()) {
                                case "Government":
                                    gov = "Government";
                                    break;
                                case "Non Government":
                                    gov = "Non Government";
                                    break;
                                case "Cultivated":
                                    cultivate = "Cultivated";
                                    break;
                                case "Uncultivated":
                                    cultivate = "Uncultivated";
                            }
                        }
                    }

                LandTM landTM = new LandTM(String.valueOf(datalist.getLand_id()),datalist.getPlan_num(), datalist.getL_area(),gov,cultivate,btnDelete);
                obList.add(landTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            land = landManageBO.searchLand((String)cbLand.getValue());
            landDetails = landManageBO.searchLandDetail((String) cbLand.getValue());
            coOwnerList = landManageBO.searchOwners((String) cbLand.getValue());

            OpenView.openView("landForm");

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            land = landManageBO.searchLand((String)cbLand.getValue());
            landDetails = landManageBO.searchLandDetail((String) cbLand.getValue());
            coOwnerList = landManageBO.searchOwners((String) cbLand.getValue());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnDelete = new Button("Remove");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

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

        LandTM landTM = new LandTM(String.valueOf(land.getLand_id()),land.getPlan_num(), land.getL_area(),gov,cultivate,btnDelete);
        obList.add(landTM);
        tblDivision.setItems(obList);
    }

    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            OpenView.openView("landManageForm2");
        });
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = landManageBO.deleteLand((String) colID.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Removed Successfully!" ).show();
                        obList.remove( tblDivision.getSelectionModel().getSelectedIndex());
                        tblDivision.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                landManageBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm",tblDivPane);
        }
    }

    @FXML
    void lblManageOnAction(MouseEvent event) {
        OpenView.openView("manageForm",tblDivPane);
    }

    @FXML
    void lblRegOnAction(MouseEvent event) {
        OpenView.openView("registrationForm",tblDivPane);
    }

    @FXML
    void lblReportOnAction(MouseEvent event) {
        OpenView.openView("reportForm",tblDivPane);
    }

    @FXML
    void lblVoteOnAction(MouseEvent event) {
        OpenView.openView("aboutUsForm",tblDivPane);
    }

}
