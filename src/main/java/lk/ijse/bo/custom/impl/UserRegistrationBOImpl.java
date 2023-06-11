package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserRegistrationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.DivisionDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserRegistrationBOImpl implements UserRegistrationBO {

    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);
    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USERDAO);

    @Override
    public List<String> loadDivision() throws SQLException {
        return divisionDAO.loadDivisionID();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User(userDTO.getEmployee(),userDTO.getDivision(),userDTO.getName(),userDTO.getNic(),userDTO.getUser(),userDTO.getPassword(),
                userDTO.getDob(),userDTO.getAge(),userDTO.getEmDate(),userDTO.getContact(), userDTO.getEmail());
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User(userDTO.getEmployee(),userDTO.getDivision(),userDTO.getName(),userDTO.getNic(),userDTO.getUser(),userDTO.getPassword(),
                userDTO.getDob(),userDTO.getAge(),userDTO.getEmDate(),userDTO.getContact(), userDTO.getEmail());
        return userDAO.update(user);
    }
}
