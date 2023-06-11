package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.LandDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.CoOwner;
import lk.ijse.entity.Land;
import lk.ijse.entity.LandDetail;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandDAOImpl implements LandDAO {
    @Override
    public  Integer getNextLandId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ORDER BY land_num DESC LIMIT 1" );

        if (resultSet.next()) {
            return (resultSet.getInt(1)+1);
        }
        return 1;
    }

    @Override
    public  List<String> loadLandID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land WHERE isArchieved=FALSE");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    @Override
    public  Land search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.land WHERE land_num =?", id);
        if (resultSet.next()) {

            return new Land(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));

        }
        return null;
    }


    @Override
    public  List<Land> searchAll() throws SQLException {
        List<Land> landList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.land WHERE isArchieved=FALSE");

        while (resultSet.next()) {
            landList.add(new Land(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3)));
        }
        return landList;
    }



    @Override
    public  Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(land_num) FROM grama_vista.land ");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.land SET isArchieved=TRUE WHERE land_num=?",id);
    }

    public boolean save (Land land) throws SQLException {
         return CrudUtil.execute("INSERT INTO grama_vista.land (land_num, plan_num, land_area) VALUES (?,?,?)",
                land.getLand_id(), land.getPlan_num(), land.getL_area());
    }

    public boolean update (Land land) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.land SET plan_num=?, land_area=? WHERE land_num=? ",
                land.getPlan_num(), land.getL_area(),land.getLand_id());
    }

    public Integer getLandNum(String plan_num) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land WHERE plan_num=? ", plan_num);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return null;
    }
}
