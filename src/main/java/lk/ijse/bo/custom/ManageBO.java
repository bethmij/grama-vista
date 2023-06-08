package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface ManageBO {

    void saveDetail(DetailDTO detail) throws SQLException;
}
