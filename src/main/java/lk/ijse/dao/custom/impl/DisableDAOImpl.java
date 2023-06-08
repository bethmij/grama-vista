package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.DisableDAO;
import lk.ijse.entity.Disable;

import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisableDAOImpl implements DisableDAO {
    @Override
    public  Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.disable_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    @Override
    public  boolean save(Disable disable) throws SQLException {
        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.disable_people (id, reg_number, disability, description) VALUES (?,?,?,?)",
                disable.getID(),disable.getCivil_ID(),disable.getDisability(),disable.getDescription());
        return isSaved;
    }

    @Override
    public  List<String> loadDisableId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.disable_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }



    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.disable_people WHERE reg_number=?", id);
    }



    @Override
    public  boolean update(Disable disable) throws SQLException {
        return  CrudUtil.execute("UPDATE grama_vista.disable_people SET reg_number=?, disability=?,description=? WHERE id=? ",
                disable.getCivil_ID(),disable.getDisability(),disable.getDescription(),disable.getID());

    }
}
