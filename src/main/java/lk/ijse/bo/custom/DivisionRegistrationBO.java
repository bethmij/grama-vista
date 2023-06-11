package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DivisionDTO;

import java.sql.SQLException;

public interface DivisionRegistrationBO extends SuperBO {
    String getNextDivisionID() throws SQLException;

    boolean saveDivision(DivisionDTO divisionDTO) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateDivision(DivisionDTO divisionDTO) throws SQLException, ClassNotFoundException;
}
