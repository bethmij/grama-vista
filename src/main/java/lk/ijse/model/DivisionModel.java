package lk.ijse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Division;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.dto.tm.DivisionTM;
import lk.ijse.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisionModel {

    public static String getNextOrderId() throws SQLException {


        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.gn_division ORDER BY division_id DESC LIMIT 1" );

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("GN0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "GN0" + id;
        }
        return "GN01";
    }

    public static boolean save(Division division) throws SQLException {

         String sql = "INSERT INTO grama_vista.gn_division (division_id, name, div_secretariat, admin_officer,land_area) " +
                            "VALUES(?, ?, ?, ?,?)";

         return CrudUtil.execute(sql, division.getDivision_id(), division.getName(), division.getDiv_Secretariat(), division.getAdmin_officer(), division.getLand_area());

    }

    public static List<String> loadDivisionID () throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.gn_division ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
        }

    public static boolean UpdatePopulation (String division_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.gn_division SET population=population+1 WHERE division_id=?",division_id);

    }

    public static boolean UpdateDeadPopulation(String division_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.gn_division SET population=population-1 WHERE division_id=?",division_id);
    }

    public static String getName (String division_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.gn_division WHERE division_id=?",division_id);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return "";
    }

    public static DivisionDTO search (String division_id) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.gn_division WHERE division_id=?",division_id);
        if(resultSet.next()){

             return new DivisionDTO(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3),
                          resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6));

        }
        return null;
    }
}





