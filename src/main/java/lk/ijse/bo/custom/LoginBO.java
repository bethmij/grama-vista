package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface LoginBO {

    List<UserDTO> searchAllUser() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
