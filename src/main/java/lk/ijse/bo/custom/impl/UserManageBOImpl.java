package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserManageBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManageBOImpl implements UserManageBO {
    UserDAO userDAO = new UserDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    @Override
    public List<String> loadEmployeeId() throws SQLException {
         return userDAO.loadUserID();
    }

    public List<UserDTO> searchAllUser() throws SQLException {
        List<User> users = userDAO.searchAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getEmployee(),user.getDivision(),user.getName(),user.getNic(),user.getUser(),user.getPassword(),
                    user.getDob(),user.getAge(),user.getEmDate(),user.getContact(), user.getEmail()));
        }
        return userDTOS;
    }

    public UserDTO searchUser(String id ) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(id);
        UserDTO userDTO = new UserDTO(user.getEmployee(),user.getDivision(),user.getName(),user.getNic(),user.getUser(),user.getPassword(),
                user.getDob(),user.getAge(),user.getEmDate(),user.getContact(), user.getEmail());
        return userDTO;
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }
}
