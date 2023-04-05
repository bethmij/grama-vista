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
import lk.ijse.dto.Maternity;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.dto.Residence;
import lk.ijse.dto.ResidenceDTO;
import lk.ijse.dto.tm.MaternityTM;
import lk.ijse.dto.tm.ResidenceTM;
import lk.ijse.model.DeadModel;
import lk.ijse.model.MaternityModel;
import lk.ijse.model.ResidenceModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MaternityManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbReg;
    public TableView tblDivision;
    public TableColumn colReg;
    public TableColumn colCivil;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colMonth;
    public TableColumn colMidWife;
    public TableColumn colAction;
    private ObservableList<MaternityTM> obList = FXCollections.observableArrayList();
    public static Maternity maternity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMaternityId();
        setCellValueFactory();
    }

    private void loadMaternityId() {
        try {
            List<String> id = MaternityModel.loadMaternityID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbReg.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
        colCivil.setCellValueFactory(new PropertyValueFactory<>("Civil"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        colMidWife.setCellValueFactory(new PropertyValueFactory<>("MidWife"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<MaternityDTO> maternityDTOS  = MaternityModel.searchAll();

            for (MaternityDTO datalist : maternityDTOS) {
                Button btnDelete = new Button("Delete");
                btnDelete.setCursor(Cursor.HAND);
                setDeleteBtnOnAction(btnDelete);

                MaternityTM maternityTM = new MaternityTM(datalist.getReg(), datalist.getCivil(), datalist.getName(), datalist.getDate(),
                        datalist.getMonth(),datalist.getMidWife(),btnDelete);
                obList.add(maternityTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            MaternityDTO maternityDTO = MaternityModel.search((String)cbReg.getValue());
            maternity= new Maternity(maternityDTO.getReg(), maternityDTO.getCivil(), maternityDTO.getName(), maternityDTO.getDate(), maternityDTO.getMidWife());

            OpenView.openView("MaternityRegistForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        MaternityDTO maternityDTO = null;
        try {
            maternityDTO =MaternityModel.search((String)cbReg.getValue());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnDelete = new Button("Delete");
        btnDelete.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(btnDelete);

        MaternityTM maternityTM = new MaternityTM(maternityDTO.getReg(), maternityDTO.getCivil(), maternityDTO.getName(), maternityDTO.getDate(),
                                        maternityDTO.getMonth(),maternityDTO.getMidWife(),btnDelete);
        obList.add(maternityTM);
        tblDivision.setItems(obList);
    }

    private void setDeleteBtnOnAction(Button btnDelete) {
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    boolean isDeleted = MaternityModel.dead((String) cbReg.getValue());

                    if(isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!" ).show();
                        obList.remove(tblDivision.getSelectionModel().getSelectedIndex());
                        tblDivision.refresh();
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                }

            }

        });
    }


}
