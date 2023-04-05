package lk.ijse.model;

import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.Disable;
import lk.ijse.dto.DisableDTO;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisableModel {
    public static Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.disable_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    public static boolean save (Disable disable) throws SQLException {
        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.disable_people (id, reg_number, disability, description) VALUES (?,?,?,?)",
                disable.getID(),disable.getCivil_ID(),disable.getDisability(),disable.getDescription());
        return isSaved;
    }

    public static List<String> loadDisableId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.disable_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    public static DisableDTO search(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT d.* , c.name FROM grama_vista.disable_people d JOIN grama_vista.civil c on c.reg_number = d.reg_number WHERE d.id=?", reg_id);
        if (resultSet.next()) {

            return new DisableDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getString(3),resultSet.getString(4));

        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.disable_people WHERE reg_number=?", id);
    }

    public static List<DisableDTO> searchAll() throws SQLException {
        List<DisableDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT d.* , c.name FROM grama_vista.disable_people d JOIN grama_vista.civil c on c.reg_number = d.reg_number");
        while (resultSet.next()) {

            datalist.add ( new DisableDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getString(3),resultSet.getString(4)));

        }
        return datalist;
    }

    public static boolean update(Disable disable) throws SQLException {
        return  CrudUtil.execute("UPDATE grama_vista.disable_people SET reg_number=?, disability=?,description=? WHERE id=? ",
                disable.getCivil_ID(),disable.getDisability(),disable.getDescription(),disable.getID());

    }
}
