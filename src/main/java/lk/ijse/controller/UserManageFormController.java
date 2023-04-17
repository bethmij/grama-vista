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
import lk.ijse.dto.Maternity;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.dto.User;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.MaternityTM;
import lk.ijse.dto.tm.UserTM;
import lk.ijse.model.MaternityModel;
import lk.ijse.model.UserModel;
import lk.ijse.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManageFormController implements Initializable {
    public AnchorPane tblDivPane;
    public ChoiceBox cbEmployee;
    public Label lblName;
    public TableView tblDivision;
    public TableColumn colEmployee;
    public TableColumn colDivision;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colUser;
    public TableColumn colLand;
    public TableColumn colAction;
    private ObservableList<UserTM> obList = FXCollections.observableArrayList();
    public static User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployeeId();
        setCellValueFactory();
    }

    private void loadEmployeeId() {
        try {
            List<String> id = UserModel.loadUserID();
            ObservableList<String> dataList = FXCollections.observableArrayList();

            for (String ids : id) {
                dataList.add(ids);
            }
            cbEmployee.setItems(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("Employee"));
        colDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("User"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("manageForm",tblDivPane);
    }


    public void btnGetAllOnAction(ActionEvent actionEvent) {
        try {
            List<UserDTO> userDTOS  =UserModel.searchAll();

            for (UserDTO datalist : userDTOS) {
                Button btnView = new Button("View more");
                btnView.setCursor(Cursor.HAND);
                setViewBtnOnAction(btnView);

                UserTM userTM = new UserTM(datalist.getEmployee(), datalist.getDivision(), datalist.getName(), datalist.getNic(), datalist.getUser(), btnView);
                obList.add(userTM);
                tblDivision.setItems(obList);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            UserDTO userDTO = UserModel.search((String)cbEmployee.getValue());
            user= new User(userDTO.getDivision(), userDTO.getEmployee(), userDTO.getNic(), userDTO.getName(), userDTO.getUser(), userDTO.getPassword(),
                    userDTO.getDob(),userDTO.getEmDate(),userDTO.getSalary(),userDTO.getContact());

            OpenView.openView("userRegistrationForm");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = null;
        try {
           userDTO =UserModel.search((String)cbEmployee.getValue());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        Button btnView = new Button("View more");
        btnView.setCursor(Cursor.HAND);
        setViewBtnOnAction(btnView);

        UserTM userTM = new UserTM(userDTO.getEmployee(), userDTO.getDivision(), userDTO.getName(), userDTO.getNic(), userDTO.getUser(), btnView);
        obList.add(userTM);
        tblDivision.setItems(obList);
    }

    private void setViewBtnOnAction(Button btnView) {
        btnView.setOnAction((e) -> {
            OpenView.openView("candidateViewForm");
        });
    }

    @FXML
    void lblLogOnAction(MouseEvent event) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            OpenView.openView("loginForm", tblDivPane);
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

    }
}
