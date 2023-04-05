package lk.ijse.model;

import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.Maternity;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaternityModel {
    public static Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.maternity_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    public static boolean save(Maternity maternity) throws SQLException {

        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.maternity_people (id, reg_number, date_of_pregnancy, mid_wife) VALUES (?,?,?,?)",
                maternity.getID(),maternity.getCivil_ID(),maternity.getDate(),maternity.getMid_wife());

        return isSaved;
    }

    public static List<String> loadMaternityID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.maternity_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    public static MaternityDTO search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT m.*,TIMESTAMPDIFF(month ,m.date_of_pregnancy,now()) AS Age, c.name FROM grama_vista.maternity_people m JOIN grama_vista.civil c on c.reg_number = m.reg_number WHERE m.id=?", id);
        if (resultSet.next()) {

            return new MaternityDTO(resultSet.getString(1), resultSet.getString(2),resultSet.getString(7),resultSet.getDate(3).toLocalDate(),
                    resultSet.getInt(6), resultSet.getString(5));

        }
        return null;
    }

    public static boolean dead(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.maternity_people WHERE id=?", id);
    }

    public static List<MaternityDTO> searchAll() throws SQLException {
        List<MaternityDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT m.*,TIMESTAMPDIFF(month ,m.date_of_pregnancy,now()) AS Age, c.name FROM grama_vista.maternity_people m JOIN grama_vista.civil c on c.reg_number = m.reg_number");
        while (resultSet.next()) {

            datalist.add (new MaternityDTO(resultSet.getString(1), resultSet.getString(2),resultSet.getString(7),resultSet.getDate(3).toLocalDate(),
                    resultSet.getInt(6), resultSet.getString(5)));

        }
        return datalist;
    }

    public static boolean update(Maternity maternity) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.maternity_people SET  reg_number=?, date_of_pregnancy=?,  mid_wife=?  WHERE id=?",
                maternity.getCivil_ID(),maternity.getDate(),maternity.getMid_wife(),maternity.getID());

    }
}
