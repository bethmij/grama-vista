package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandModel {
    public static Integer getNextLandId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ORDER BY land_num DESC LIMIT 1" );

        if (resultSet.next()) {
            return (resultSet.getInt(1)+1);
        }
        return 1;
    }

    public static boolean save(Land land, List<LandDetail> landDetailList, List<Owner> ownerList) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandSaved = CrudUtil.execute("INSERT INTO grama_vista.land (land_num, plan_num, land_area) VALUES (?,?,?)",
                    land.getLand_id(), land.getPlan_num(), land.getL_area());
            if (isLandSaved) {
                boolean isOwnerSaved = OwnerModel.save(ownerList);
                if (isOwnerSaved) {
                    boolean isDetailSaved = LandTypeModel.saveDetail(landDetailList);
                    if (isDetailSaved) {
                        con.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }

    }

    public static List<String> loadLandID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    public static Land search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.land WHERE land_num =?", id);
        if (resultSet.next()) {

            return new Land(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));

        }
        return null;
    }


    public static List<Land> searchAll() throws SQLException {
        List<Land> landList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.land ");

        while (resultSet.next()) {
            landList.add(new Land(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3)));
        }
        return landList;
    }

    public static boolean update(Land land, List<LandDetail> landDetailList, List<Owner> ownerLists) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandUpdated = CrudUtil.execute("UPDATE grama_vista.land SET plan_num=?, land_area=? WHERE land_num=? ",
                     land.getPlan_num(), land.getL_area(),land.getLand_id());
            if (isLandUpdated) {
                boolean isOwnerUpdated = OwnerModel.update(ownerLists);
                if (isOwnerUpdated) {
                    boolean isDetailUpdated = LandTypeModel.updateDetail(landDetailList);
                    if (isDetailUpdated) {
                        con.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }
    }

    public static Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(land_num) FROM grama_vista.land ");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM grama_vista.land WHERE land_num=?",id);
    }
}
