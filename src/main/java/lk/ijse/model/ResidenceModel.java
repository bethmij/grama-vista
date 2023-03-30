package lk.ijse.model;

import lk.ijse.dto.Candidate;
import lk.ijse.dto.Residence;
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
}
