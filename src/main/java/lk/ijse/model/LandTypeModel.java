package lk.ijse.model;

import lk.ijse.dto.LandDetail;
import lk.ijse.dto.Owner;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.controller.AddLandTypeFormController.landDetailList;

public class LandTypeModel {


    public static boolean saveDetail(List<LandDetail> landDetailList) throws SQLException {
        for(LandDetail list : landDetailList) {
            if(!saveDetail(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean saveDetail( LandDetail landDetail) throws SQLException {

        String sql = "INSERT INTO grama_vista.land_detail ( type_id, land_num) VALUES (?,?)";

        return CrudUtil.execute(sql, landDetail.getType_id(), landDetail.getLand_num());
    }

    public static Integer getTypeId (String landType) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT type_id FROM grama_vista.land_type WHERE name=?",landType);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    public static List<LandDetail> searchLandDetail(String id) throws SQLException {
        List<LandDetail> landDetails = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT ld.* , lt.name FROM grama_vista.land_type lt JOIN grama_vista.land_detail ld on lt.type_id = ld.type_id WHERE ld.land_num=?",id);
        while (resultSet.next()){
            landDetails.add(new LandDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3) ));
        }
        return landDetails;
    }

    public static List<LandDetail> searchAllLandDetail() throws SQLException {
        List<LandDetail> landDetail = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT ld.* , lt.name FROM grama_vista.land_type lt JOIN grama_vista.land_detail ld on lt.type_id = ld.type_id ");
        while (resultSet.next()){
            landDetail.add(new LandDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3) ));
        }
        return landDetail;
    }

    public static boolean updateDetail(List<LandDetail> landDetailList) throws SQLException {
        for (LandDetail list : landDetailList) {
            if (!updateDetail(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateDetail(LandDetail landDetail) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.land_detail SET land_num=? WHERE type_id=?",
                landDetail.getLand_num(), landDetail.getType_id());
    }
}
