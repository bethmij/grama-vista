package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DivisionDTO;

import java.sql.SQLException;

public interface DivisionRegistrationBO {
    String getNextDivisionID() throws SQLException;

    boolean saveDivision(DivisionDTO divisionDTO) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateDivision(DivisionDTO divisionDTO) throws SQLException;
}
