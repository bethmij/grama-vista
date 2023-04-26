package lk.ijse.model;

import lk.ijse.dto.Detail;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;

public class DetailModel {
    public static boolean save(Detail details) throws SQLException {
        return CrudUtil.execute("INSERT INTO grama_vista.detail ( function_name, user, id, name, time, date) " +
                "VALUES (?,?,?,?,?,?)",details.getFunction_name(),details.getUser(),details.getId(),details.getName(),details.getTime(),details.getDate());
    }
}
