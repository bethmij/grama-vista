package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PasswordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class PasswordBOImpl implements PasswordBO {

    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USERDAO);

    @Override
    public UserDTO searchByUser(String users) throws SQLException {
        User user = userDAO.searchbyUser(users);
        UserDTO userDTO = new UserDTO(user.getEmployee(),user.getDivision(),user.getName(),user.getNic(),user.getUser(),user.getPassword(),
                user.getDob(),user.getAge(),user.getEmDate(),user.getContact(), user.getEmail());
        return userDTO;
    }

    @Override
    public boolean updatePassword(String hashed, String users) throws SQLException {
        return userDAO.updatePass(hashed,users);
    }
}
