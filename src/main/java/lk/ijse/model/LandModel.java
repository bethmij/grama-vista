package lk.ijse.model;

import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandModel {
    public static String getNextLandId() throws SQLException {


        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ORDER BY land_num DESC LIMIT 1" );

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("L00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "L00" + id;
        }
        return "L001";
    }

    public static List<String> loadLandId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }
}
