package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Division;
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

         boolean isSaved = CrudUtil.execute(sql, division.getDivision_id(), division.getName(), division.getDiv_Secretariat(), division.getAdmin_officer(), division.getLand_area());

         return isSaved;

    }

    public static List<String> loadDivisionID () throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT division_id FROM grama_vista.gn_division ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
        }
    }



