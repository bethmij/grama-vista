package lk.ijse.bo.custom;

import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface PasswordBO {

    UserDTO searchByUser(String users) throws SQLException;

    boolean updatePassword(String hashed, String users) throws SQLException;

}
