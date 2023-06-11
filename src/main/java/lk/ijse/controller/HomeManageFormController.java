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
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.HomeManageBO;
import lk.ijse.bo.custom.impl.HomeManageBOImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.ResidenceTM;
import lk.ijse.entity.Residence;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbHome;
    public TableView tblDivision;
    public TableColumn colID;
    public TableColumn colDivision;
    public TableColumn colAddress;
    public TableColumn colHolder;
    public TableColumn colAction;
    public TableColumn colAction1;
    private ObservableList<ResidenceTM> obList = FXCollections.observableArrayList();
    public static Residence residence;
    public static List<CivilDTO> civilDetail;
    HomeManageBO homeManageBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.HOMEMANAGEBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadResidenceID();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colHolder.setCellValueFactory(new PropertyValueFactory<>("Holder"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadResidenceID() {
        try {
            List<String> id = homeManageBO.loadResidenceId();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbHome.setItems(dataList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<ResidenceDTO> residenceDTOS  = homeManageBO.searchAllResidence();

            for (ResidenceDTO datalist : residenceDTOS) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);


                ResidenceTM residenceTM = new ResidenceTM(datalist.getID(),datalist.getDivision(),datalist.getAddress(),datalist.getHolder(),btnDelete);
                obList.add(residenceTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            ResidenceDTO residenceDTO = homeManageBO.searchResidence((String) cbHome.getValue());
            residence= new Residence(residenceDTO.getID(),residenceDTO.getDivision(),residenceDTO.getHolder(),residenceDTO.getAddress(),residenceDTO.getMember(),
                    residenceDTO.getBelow(),residenceDTO.getType(),residenceDTO.getElec(),residenceDTO.getWater());

            OpenView.openView("homeRegistrationForm");
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        ResidenceDTO residenceDTO = null;
        try {
            residenceDTO = homeManageBO.searchResidence((String) cbHome.getValue());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);


        ResidenceTM residenceTM = new ResidenceTM(residenceDTO.getID(),residenceDTO.getDivision(),residenceDTO.getAddress(),residenceDTO.getHolder(),btnDelete);
        obList.add(residenceTM);
        tblDivision.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = homeManageBO.deleteResidence((String) colID.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));
                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove( tblDivision.getSelectionModel().getSelectedIndex());
                        tblDivision.refresh();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }

  /*  private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            try {
              civilDetail = homeManageBO. getCivilDetail((String) colID.getCellData(tblDivision.getSelectionModel().getSelectedIndex()));
                System.out.println(civilDetail);
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
            OpenView.openView("homecivil");

        });
    }*/

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi",LocalTime.now(), LocalDate.now(),"");
            try {
                homeManageBO.saveDetail(detail);
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
