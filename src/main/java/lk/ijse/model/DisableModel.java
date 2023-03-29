package lk.ijse.model;

import lk.ijse.dto.Disable;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisableModel {
    public static Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.disable_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    public static boolean save (Disable disable) throws SQLException {
        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.disable_people (id, reg_number, disability, description) VALUES (?,?,?,?)",
                disable.getID(),disable.getCivil_ID(),disable.getDisability(),disable.getDisability());
        return isSaved;
    }
}
