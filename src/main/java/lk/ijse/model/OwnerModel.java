package lk.ijse.model;

import lk.ijse.dto.Owner;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static List<Owner> searchOwner (String id ) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.co_ownership WHERE land_num=?",id);
        while (resultSet.next()){
            ownerList.add(new Owner(resultSet.getInt(2),resultSet.getString(1), resultSet.getString(4),resultSet.getDouble(3)));
        }
        return ownerList;
    }

    public static List<Owner> searchAllOwner() throws SQLException {
        List<Owner> ownerLists = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.co_ownership");
        while (resultSet.next()){
            ownerLists.add(new Owner(resultSet.getInt(2),resultSet.getString(1), resultSet.getString(4),resultSet.getDouble(3)));
        }
        return ownerLists;
    }

    public static boolean update(List<Owner> ownerLists) throws SQLException {
        for (Owner list : ownerLists) {
            if (!update(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean update(Owner owner) throws SQLException {
        //System.out.println(owner);
        boolean isSaved = CrudUtil.execute("UPDATE grama_vista.co_ownership SET reg_number=?, land_percentage=?,lot_num=?  WHERE land_num=?",
                owner.getCivil_id(), owner.getPercentage(), owner.getLot_num(), owner.getLand_id());
        System.out.println(isSaved);
        return isSaved;
    }
}


