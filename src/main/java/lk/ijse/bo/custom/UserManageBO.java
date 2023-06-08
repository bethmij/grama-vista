package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserManageBO {
    List<String> loadEmployeeId() throws SQLException;

    List<UserDTO> searchAllUser() throws SQLException;

    UserDTO searchUser(String id ) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
}
