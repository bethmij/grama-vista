package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface RegistrationBO extends SuperBO {

    void saveDetail(DetailDTO detail) throws SQLException;
}
