package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.AddLandTypeBO;
import lk.ijse.bo.custom.impl.AddLandTypeBOImpl;
import lk.ijse.dao.custom.LandTypeDAO;
import lk.ijse.dto.LandDetailDTO;
import lk.ijse.entity.LandDetail;
import lk.ijse.dao.custom.impl.LandTypeDAOImpl;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.LandManageFormController.land;

public class AddLandTypeFormController implements Initializable {


    public TextField txtLand;
    public ChoiceBox cbType;
    public static List<LandDetailDTO> landDetailList = new ArrayList<>();
    public Label lblLand;
    AddLandTypeBO addLandTypeBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ADDLANDTYPEBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLandType();
        lblLand.setText(LandFormController.land_id);

        /*if(landDetails.size()==2) {
            cbType.setValue(landDetails.get(1).getLand_type());
        }else
            cbType.setValue("");*/
    }

    private void loadLandType() {
        String[] type = new String[]{"Government","Non Government","Cultivated", "Uncultivated"};
        ObservableList<String> dataList = FXCollections.observableArrayList(type);
        cbType.setItems(dataList);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        String[] land_num = lblLand.getText().split("L00");
        Integer type_id = addLandTypeBO.getLandTypeID((String) cbType.getValue());

        if ((!(land == null)))
            landDetailList.add(new LandDetailDTO(type_id,land.getLand_id(),(String)cbType.getValue() ));
        else
            landDetailList.add(new LandDetailDTO(type_id, Integer.valueOf(land_num[1]),(String)cbType.getValue() ));


        if(landDetailList !=null)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully !").show();
    }


}
