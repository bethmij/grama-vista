package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface PasswordBO extends SuperBO {

    UserDTO searchByUser(String users) throws SQLException;

    boolean updatePassword(String hashed, String users) throws SQLException;

}
