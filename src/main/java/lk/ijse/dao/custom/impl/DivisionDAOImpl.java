package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.entity.Division;

import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisionDAOImpl implements DivisionDAO {

    @Override
    public  String getNextOrderId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.gn_division ORDER BY division_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    public String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("GN0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "GN0" + id;
        }
        return "GN01";
    }

    @Override
    public  boolean save(Division division) throws SQLException {

        String sql = "INSERT INTO grama_vista.gn_division (division_id, name, div_secretariat, admin_officer,land_area) " +
                "VALUES(?, ?, ?, ?,?)";

        return CrudUtil.execute(sql, division.getDivision_id(), division.getName(), division.getDiv_Secretariat(), division.getAdmin_officer(), division.getLand_area());

    }

    @Override
    public  List<String> loadDivisionID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.gn_division ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()) {
            id.add(resultSet.getString(1));
        }

        return id;
    }

    @Override
    public  boolean UpdatePopulation(String division_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.gn_division SET population=population+1 WHERE division_id=?", division_id);

    }

    @Override
    public  boolean UpdateDeadPopulation(String division_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.gn_division SET population=population-1 WHERE division_id=?", division_id);
    }

    @Override
    public  String getName(String division_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.gn_division WHERE division_id=?", division_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    @Override
    public  Division search(String division_id) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.gn_division WHERE division_id=?", division_id);
        if (resultSet.next()) {

            return new Division(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4),resultSet.getDouble(6), resultSet.getInt(5));

        }
        return null;
    }

    @Override
    public  boolean delete(String division_id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.gn_division WHERE division_id=?", division_id);
    }

    @Override
    public  List<Division> searchAll() throws SQLException {
        List<Division> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.gn_division ");
        while (resultSet.next()) {

            datalist.add((new Division(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4),resultSet.getDouble(6), resultSet.getInt(5))));

        }
        return datalist;
    }

    @Override
    public  boolean update(Division division) throws SQLException {

        String sql = "UPDATE grama_vista.gn_division SET  name=?, div_secretariat=?, admin_officer=?,land_area=? " +
                "WHERE division_id = ?";
        return CrudUtil.execute(sql, division.getName(), division.getDiv_Secretariat(), division.getAdmin_officer(), division.getLand_area(), division.getDivision_id());
    }

    @Override
    public  Integer getPopulation() throws SQLException {
        String division_id = "GN01";
        ResultSet resultSet =  CrudUtil.execute("SELECT population FROM grama_vista.gn_division WHERE division_id=?",division_id);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }
}








