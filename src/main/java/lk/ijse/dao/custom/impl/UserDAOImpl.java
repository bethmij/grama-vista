package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;

import lk.ijse.dao.custom.impl.util.CrudUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public  boolean save(User user) throws SQLException {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String sql = "INSERT INTO grama_vista.users (employee_num, division_id, nic, name, user, password, dob, date_of_employment, email, contact) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql, user.getEmployee(), user.getDivision(), user.getNic(), user.getName(),
                user.getUser(),hashed, user.getDob(), user.getEmDate(), user.getEmail(), user.getContact()
                );

        return isSaved;
    }

    @Override
    public  List<String> loadUserID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT employee_num FROM grama_vista.users ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    @Override
    public  User search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users  WHERE employee_num =?", id);
        if (resultSet.next()) {

            return new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7).toLocalDate(),resultSet.getInt(11),
                    resultSet.getDate(8).toLocalDate(),resultSet.getInt(9), resultSet.getString(10));

        }
        return null;
    }

    @Override
    public  List<User> searchAll() throws SQLException {
        List<User> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users ");
        while (resultSet.next()) {

            datalist.add (new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7).toLocalDate(), resultSet.getInt(11),
                    resultSet.getDate(8).toLocalDate(),resultSet.getInt(9), resultSet.getString(10)));

        }
        return datalist;
    }

    @Override
    public  boolean update(User user) throws SQLException {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String sql = "UPDATE grama_vista.users SET  division_id=?, nic=?, name=?, user=?, password=?, dob=?, date_of_employment=?, email=?, contact=?  WHERE employee_num=?";

        return CrudUtil.execute(sql,  user.getDivision(), user.getNic(), user.getName(),
                user.getUser(),hashed, user.getDob(), user.getEmDate(), user.getEmail(), user.getContact(),user.getEmployee());

    }

    @Override
    public  User searchbyUser(String user) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users  WHERE user =?", user);
        if (resultSet.next()) {

            return new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7).toLocalDate(),resultSet.getInt(11),
                    resultSet.getDate(8).toLocalDate(),resultSet.getInt(9), resultSet.getString(10));

        }
        return null;
    }

    @Override
    public  boolean updatePass(String password, String user) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.users SET password=? WHERE user=?",password,user);
    }

    @Override
    public  boolean delete (String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.users WHERE employee_num=?", id);
    }
}
