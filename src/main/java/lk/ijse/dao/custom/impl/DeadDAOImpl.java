package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.DeadDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Dead;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeadDAOImpl implements DeadDAO {
    @Override
    public  Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.dead_people ORDER BY id DESC LIMIT 1");

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return (id+1);
        }
        return 1;
    }




    @Override
    public  List<String> loadDeadId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.dead_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }


    @Override
    public boolean save(Dead dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public  boolean update(Dead dead) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.dead_people SET reg_number=?, dead_date=? WHERE id=?",
                    dead.getCivil_ID(),  dead.getDate(),dead.getReg_id());



    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE  FROM grama_vista.dead_people WHERE id=?", id);
    }

    @Override
    public Dead search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Dead> searchAll() throws SQLException {
        return null;
    }

}
