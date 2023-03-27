package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Division;

import java.sql.*;

public class DivisionModel {

    public static String getNextOrderId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT division_id FROM grama_vista.gn_division ORDER BY division_id DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

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
            Connection con = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO grama_vista.gn_division (division_id, name, div_secretariat, admin_officer,land_area) " +
                    "VALUES(?, ?, ?, ?,?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, division.getDivision_id());
            pstm.setString(2, division.getName());
            pstm.setString(3, division.getDiv_Secretariat());
            pstm.setString(4, division.getAdmin_officer());
            pstm.setDouble(5, division.getLand_area());

            return pstm.executeUpdate() > 0;
        }


}
