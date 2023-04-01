package lk.ijse.model;

import lk.ijse.dto.Owner;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OwnerModel {

    public static boolean save(List<Owner> ownerTypeList) throws SQLException {
        for (Owner list : ownerTypeList) {
            if (!save(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(Owner owner) throws SQLException {

        return CrudUtil.execute("INSERT INTO grama_vista.co_ownership (reg_number, land_num, land_percentage, lot_num) VALUES (?,?,?,?)",
                owner.getCivil_id(), owner.getLand_id(), owner.getPercentage(), owner.getLot_num());
    }

}


