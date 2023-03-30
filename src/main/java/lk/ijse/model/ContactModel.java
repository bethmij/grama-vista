package lk.ijse.model;

import lk.ijse.dto.Civil;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;

public class ContactModel {
    public static boolean save(Civil civil) throws SQLException {

        String sql = "INSERT INTO grama_vista.contact (reg_number, contact_num) " +
                "VALUES (?,?)";

        boolean isSaved = CrudUtil.execute(sql,civil.getId(),civil);


        return isSaved;

    }
}
