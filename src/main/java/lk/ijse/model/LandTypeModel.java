package lk.ijse.model;

import lk.ijse.dto.LandType;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class LandTypeModel {

    public static boolean save(List<LandType> landTypeList) throws SQLException {
        for(LandType list : landTypeList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save( LandType landType ) throws SQLException {

        String sql = "INSERT INTO grama_vista.land_type (type_id, name) VALUES (?,?)";

        return CrudUtil.execute(sql,landType.getLand_id(),landType.getLand_type());
    }

    public static boolean saveDetail(List<LandType> landTypeList) throws SQLException {
        for(LandType list : landTypeList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

}
