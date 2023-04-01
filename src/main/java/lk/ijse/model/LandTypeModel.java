package lk.ijse.model;

import lk.ijse.dto.LandDetail;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

}
