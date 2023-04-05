package lk.ijse.model;

import lk.ijse.dto.*;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidenceModel {

    public static boolean save(Residence residence) throws SQLException {
        String sql = "INSERT INTO grama_vista.residence (home_id, division_id, house_holder, address, member_count, count_below_18, residence_type, electricity, water_supply) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql,
                residence.getHome_id(), residence.getDivision_id(), residence.getHouse_holder_name(), residence.getAddress(), residence.getMember_count(),
                residence.getCount_below_18(), residence.getResidence_type(), residence.getElectricity(), residence.getWater_supply());


        return isSaved;
    }



    public static List<String> loadResidenceID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT home_id FROM grama_vista.residence ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    public static ResidenceDTO search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.residence WHERE home_id=?", id);
        if (resultSet.next()) {

            return new ResidenceDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4), resultSet.getString(3),
                    resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8), resultSet.getString(9));

        }
        return null;
    }

    public static List<ResidenceDTO> searchAll() throws SQLException {
        List<ResidenceDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.residence ");
        while (resultSet.next()) {

            datalist.add ( new ResidenceDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4), resultSet.getString(3),
                    resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8), resultSet.getString(9)));

        }
        return datalist;
    }

    public static boolean update(Residence residence) throws SQLException {

        String sql = "UPDATE grama_vista.residence SET division_id=?, house_holder=?, address=?, member_count=?, count_below_18=?," +
                    " residence_type=?, electricity=?, water_supply=?  WHERE home_id=?";

        return CrudUtil.execute(sql, residence.getDivision_id(), residence.getHouse_holder_name(), residence.getAddress(), residence.getMember_count(),
                residence.getCount_below_18(), residence.getResidence_type(), residence.getElectricity(), residence.getWater_supply(), residence.getHome_id());

    }

    public static boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.residence WHERE home_id=?", id);
    }
}
