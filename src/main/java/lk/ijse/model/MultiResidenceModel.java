package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Contact;
import lk.ijse.dto.MultiResidence;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MultiResidenceModel {

    public static boolean save(List<MultiResidence> multiResidenceList ) throws SQLException {
        for(MultiResidence list : multiResidenceList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save( MultiResidence multiResidence ) throws SQLException {

        String sql = "INSERT INTO grama_vista.multi_residence (home_id, reg_number) " +
                "VALUES (?,?)";

        return CrudUtil.execute(sql,multiResidence.getResidence_id(),multiResidence.getCivil_id());
    }
}