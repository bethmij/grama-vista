package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {

    List<String> loadUserID() throws SQLException;

    User searchbyUser(String user) throws SQLException;

    boolean updatePass(String password, String user) throws SQLException;


}

