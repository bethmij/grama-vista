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
import lk.ijse.bo.custom.CivilManageBO;
import lk.ijse.bo.custom.impl.CivilManageBOImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.CivilTM;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CivilManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public Label lblName;
    public TableView tbl;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colAction;
    public ComboBox cmbID;
    public TableColumn colRelation;
    private ObservableList<CivilTM> obList = FXCollections.observableArrayList();

    public static CivilDTO civilDTO;
    public static List<ContactDTO> contactList = new ArrayList<>();
    public static List<MultiResidenceDTO> multiResidenceList =new ArrayList<>();
    public static Civil2DTO civil2DTO;
    public static String id;
    CivilManageBO civilManageBO = new CivilManageBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadCivilId();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRelation.setCellValueFactory(new PropertyValueFactory<>("Relation"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void loadCivilId() {
        List<String> id = null;
        try {
            id = civilManageBO.loadCivilId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : id) {
            dataList.add(ids);
        }
        cmbID.setItems(dataList);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }


    public void btnGetAllOnAction(ActionEvent actionEvent) {

        try {
            List<CivilDTO> civilDTOList  = civilManageBO.searchAllCivil();

            for (CivilDTO datalist : civilDTOList) {
                Button btnView = new Button("View more");
                btnView.setCursor(Cursor.HAND);
                setViewBtnOnAction(btnView);

                CivilTM civilTM = new CivilTM(datalist.getID(),datalist.getName(),
                        datalist.getNic(),datalist.getAddress(), datalist.getRelation(), btnView);
                obList.add(civilTM);
                tbl.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            CivilDTO civilDTOs = civilManageBO.searchCivil((String) cmbID.getValue());
            civilDTO= new CivilDTO(civilDTOs.getID(),null, civilDTOs.getName(), civilDTOs.getNic(), civilDTOs.getAddress(),
                    (civilDTOs.getDob()), null,civilDTOs.getGender(), civilDTOs.getMarriage(),civilDTOs.getRelation(),
                    civilDTOs.getEducation(),civilDTOs.getSchool(),civilDTOs.getOccupation(),
                    civilDTOs.getWork(),civilDTOs.getSalary(),civilDTOs.getEmail());

            contactList = civilManageBO.searchContact((String) cmbID.getValue());
            multiResidenceList = civilManageBO.searchResidence((String) cmbID.getValue());

            OpenView.openView("individualForm");
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        try {
            civilDTO = civilManageBO.searchCivil((String) cmbID.getValue());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);

        CivilTM civilTM = new CivilTM(civilDTO.getID(),civilDTO.getName(),
                            civilDTO.getNic(),civilDTO.getAddress(),civilDTO.getRelation(),btnView);
        contactList = civilManageBO.searchContact((String) cmbID.getValue());
        multiResidenceList = civilManageBO.searchResidence((String) cmbID.getValue());

        obList.add(civilTM);
        tbl.setItems(obList);
    }



    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            try {
                CivilDTO civilDTOs = civilManageBO.searchCivil((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));
                id = (String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex());
                List<ContactDTO> contactLists = civilManageBO.searchContact((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));
                List<MultiResidenceDTO> multiResidenceLists = civilManageBO.searchResidence((String) colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

                Integer contact1 = null, contact2 = null;
                if(contactLists.size()==1)
                    contact1=contactLists.get(0).getContact();

                if(contactLists.size()==2)
                    contact2=contactLists.get(1).getContact();

                civil2DTO = new Civil2DTO(civilDTOs.getID(),civilDTOs.getName(),civilDTOs.getImage(),civilDTOs.getNic(),civilDTOs.getAge(),civilDTOs.getAddress(),civilDTOs.getDob(),
                        civilDTOs.getGender(),civilDTOs.getMarriage(), civilDTOs.getRelation(),civilDTOs.getEducation(),civilDTOs.getSchool(),civilDTOs.getOccupation(),
                        civilDTOs.getWork(),civilDTOs.getSalary(),contact1,contact2);

            } catch (SQLException | ClassNotFoundException ex) {
               new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
            OpenView.openView("civilViewForm");

        });
    }

    public void cmbIDOnAction(ActionEvent actionEvent) {
        try {
            lblName.setText(civilManageBO.getCivilName((String) cmbID.getValue()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                civilManageBO.saveDetail(detail);
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
