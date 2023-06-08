package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CoOwnerDAO;
import lk.ijse.entity.CoOwner;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoOwnerDAOImpl implements CoOwnerDAO {

    @Override
    public  boolean save(List<CoOwner> coOwnerTypeList) throws SQLException {
        for (CoOwner list : coOwnerTypeList) {
            if (!save(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(CoOwner coOwner) throws SQLException {

        return CrudUtil.execute("INSERT INTO grama_vista.co_ownership (reg_number, land_num, land_percentage, lot_num) VALUES (?,?,?,?)",
                coOwner.getCivil_id(), coOwner.getLand_id(), coOwner.getPercentage(), coOwner.getLot_num());
    }

    @Override
    public  List<CoOwner> searchOwner(String id) throws SQLException {
        List<CoOwner> coOwnerList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.co_ownership WHERE land_num=?",id);
        while (resultSet.next()){
            coOwnerList.add(new CoOwner(resultSet.getInt(2),resultSet.getString(1), resultSet.getString(4),resultSet.getDouble(3)));
        }
        return coOwnerList;
    }

    @Override
    public  List<CoOwner> searchAllOwner() throws SQLException {
        List<CoOwner> coOwnerLists = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.co_ownership");
        while (resultSet.next()){
            coOwnerLists.add(new CoOwner(resultSet.getInt(2),resultSet.getString(1), resultSet.getString(4),resultSet.getDouble(3)));
        }
        return coOwnerLists;
    }

    @Override
    public  boolean update(List<CoOwner> coOwnerLists) throws SQLException {
        for (CoOwner list : coOwnerLists) {
            if (!update(list)) {
                return false;
            }
        }
        return true;
    }

    public boolean update(CoOwner coOwner) throws SQLException {
        boolean isSaved = CrudUtil.execute("UPDATE grama_vista.co_ownership SET reg_number=?, land_percentage=?,lot_num=?  WHERE land_num=?",
                coOwner.getCivil_id(), coOwner.getPercentage(), coOwner.getLot_num(), coOwner.getLand_id());
        System.out.println(isSaved);
        return isSaved;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CoOwner search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CoOwner> searchAll() throws SQLException {
        return null;
    }

}


