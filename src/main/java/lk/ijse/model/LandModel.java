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
            Integer id = getLandNum(land.getPlan_num());
            if (isLandSaved) {

                for(int i=0; i<ownerList.size(); i++){
                    ownerList.get(i).setLand_id(id);
                }
                boolean isOwnerSaved = OwnerModel.save(ownerList);
                if (isOwnerSaved) {

                    for(int i=0; i<landDetailList.size(); i++){
                        landDetailList.get(i).setLand_num(id);
                    }
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

    private static Integer getLandNum(String plan_num) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land WHERE plan_num=? ",plan_num);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    public static List<String> loadLandID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land WHERE isArchieved=FALSE");
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
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.land WHERE isArchieved=FALSE");

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
            Integer id = getLandNum(land.getPlan_num());
            if (isLandUpdated) {

                for(int i=0; i<ownerLists.size(); i++){
                    ownerLists.get(i).setLand_id(id);
                }

                boolean isOwnerUpdated = OwnerModel.save(ownerLists);
                if (isOwnerUpdated) {

                    for(int i=0; i<landDetailList.size(); i++){
                        landDetailList.get(i).setLand_num(id);
                    }

                    boolean isDetailUpdated = LandTypeModel.saveDetail(landDetailList);
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
        return CrudUtil.execute("UPDATE grama_vista.land SET isArchieved=TRUE WHERE land_num=?",id);
    }
}
