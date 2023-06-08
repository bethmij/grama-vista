package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ResidenceDAO;

import lk.ijse.entity.Residence;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidenceDAOImpl implements ResidenceDAO {

    @Override
    public  boolean save(Residence residence) throws SQLException {
        String sql = "INSERT INTO grama_vista.residence (home_id, division_id, house_holder, address, member_count, count_below_18, residence_type, electricity, water_supply) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql,
                residence.getHome_id(), residence.getDivision_id(), residence.getHouse_holder_name(), residence.getAddress(), residence.getMember_count(),
                residence.getCount_below_18(), residence.getResidence_type(), residence.getElectricity(), residence.getWater_supply());


        return isSaved;
    }



    @Override
    public  List<String> loadResidenceID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT home_id FROM grama_vista.residence ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    @Override
    public  Residence search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.residence WHERE home_id=?", id);
        if (resultSet.next()) {

            return new Residence(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4), resultSet.getString(3),
                    resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8), resultSet.getString(9));

        }
        return null;
    }

    @Override
    public  List<Residence> searchAll() throws SQLException {
        List<Residence> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.residence ");
        while (resultSet.next()) {

            datalist.add ( new Residence(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4), resultSet.getString(3),
                    resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8), resultSet.getString(9)));

        }
        return datalist;
    }

    @Override
    public  boolean update(Residence residence) throws SQLException {

        String sql = "UPDATE grama_vista.residence SET division_id=?, house_holder=?, address=?, member_count=?, count_below_18=?," +
                    " residence_type=?, electricity=?, water_supply=?  WHERE home_id=?";

        return CrudUtil.execute(sql, residence.getDivision_id(), residence.getHouse_holder_name(), residence.getAddress(), residence.getMember_count(),
                residence.getCount_below_18(), residence.getResidence_type(), residence.getElectricity(), residence.getWater_supply(), residence.getHome_id());

    }

    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.residence WHERE home_id=?", id);
    }

    @Override
    public  Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(home_id) FROM grama_vista.residence ");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  String getDivisionId(String residence) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.residence WHERE home_id=?",residence);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }


}
