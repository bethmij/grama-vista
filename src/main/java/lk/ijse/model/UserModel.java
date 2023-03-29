package lk.ijse.model;

import lk.ijse.dto.User;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;

public class UserModel {

    public static boolean save(User user) throws SQLException {
        String sql = "INSERT INTO grama_vista.users (employee_num, division_id, nic, name, user, password, dob, date_of_employment, salary, contact) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql,
                user.getEmployee_num(),
                user.getDivision_id(),
                user.getNic(),
                user.getName(),
                user.getUser(),
                user.getPassword(),
                user.getDate(),
                user.getEmployee_date(),
                user.getSalary(),
                user.getContact()
                );

        return isSaved;
    }
}
