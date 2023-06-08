package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface RegistrationBO {

    void saveDetail(DetailDTO detail) throws SQLException;
}
