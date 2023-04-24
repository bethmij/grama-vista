package lk.ijse.model;

import lk.ijse.dto.MaternityDTO;
import lk.ijse.dto.User;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.CrudUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    public static boolean save(User user) throws SQLException {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String sql = "INSERT INTO grama_vista.users (employee_num, division_id, nic, name, user, password, dob, date_of_employment, salary, contact) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql, user.getEmployee_num(), user.getDivision_id(), user.getNic(), user.getName(),
                user.getUser(),hashed, user.getDate(), user.getEmployee_date(), user.getSalary(), user.getContact()
                );

        return isSaved;
    }

    public static List<String> loadUserID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT employee_num FROM grama_vista.users ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    public static UserDTO search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users  WHERE employee_num =?", id);
        if (resultSet.next()) {

            return new UserDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7).toLocalDate(),resultSet.getInt(12),
                    resultSet.getDate(8).toLocalDate(),resultSet.getDouble(9), resultSet.getInt(10),resultSet.getString(11));

        }
        return null;
    }

    public static List<UserDTO> searchAll() throws SQLException {
        List<UserDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users ");
        while (resultSet.next()) {

            datalist.add (new UserDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7).toLocalDate(),resultSet.getInt(12),
                    resultSet.getDate(8).toLocalDate(),resultSet.getDouble(9), resultSet.getInt(10),resultSet.getString(11)));

        }
        return datalist;
    }

    public static boolean update(User user) throws SQLException {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String sql = "UPDATE grama_vista.users SET  division_id=?, nic=?, name=?, user=?, password=?, dob=?, date_of_employment=?, salary=?, contact=?  WHERE employee_num=?";

        return CrudUtil.execute(sql,  user.getDivision_id(), user.getNic(), user.getName(),
                user.getUser(),hashed, user.getDate(), user.getEmployee_date(), user.getSalary(), user.getContact(),user.getEmployee_num());

    }

    public static UserDTO searchbyUser(String user) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year ,dob,now()) AS Age FROM grama_vista.users  WHERE user =?", user);
        if (resultSet.next()) {

            return new UserDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7).toLocalDate(),resultSet.getInt(12),
                    resultSet.getDate(8).toLocalDate(),resultSet.getDouble(9), resultSet.getInt(10),resultSet.getString(11));

        }
        return null;
    }

    public static boolean updatePass(String password, String user) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.users SET password=? WHERE user=?",password,user);
    }
}
