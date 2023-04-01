package lk.ijse.model;

import lk.ijse.dto.Maternity;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaternityModel {
    public static Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.maternity_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    public static boolean save(Maternity maternity) throws SQLException {

        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.maternity_people (id, reg_number, date_of_pregnancy, months, mid_wife) VALUES (?,?,?,?,?)",
                maternity.getID(),maternity.getCivil_ID(),maternity.getDate(),maternity.getMonths(),maternity.getMid_wife());

        return isSaved;
    }
}
