package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MaternityDAO;
import lk.ijse.entity.Maternity;

import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaternityDAOImpl implements MaternityDAO {
    @Override
    public  Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.maternity_people ORDER BY id DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return id+1;
        }
        return 1;
    }

    @Override
    public  boolean save(Maternity maternity) throws SQLException {

        boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.maternity_people (id, reg_number, mid_wife, register_date,months) VALUES (?,?,?,?,?)",
                maternity.getID(),maternity.getCivil_ID(),maternity.getMid_wife(), LocalDate.now(),maternity.getMonths());

        return isSaved;
    }

    @Override
    public  List<String> loadMaternityID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.maternity_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }



    @Override
    public  boolean dead(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.maternity_people WHERE id=?", id);
    }



    @Override
    public  boolean update(Maternity maternity) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.maternity_people SET  reg_number=?,  mid_wife=? ,months=? WHERE id=?",
                maternity.getCivil_ID(),maternity.getMid_wife(),maternity.getMonths(),maternity.getID());

    }
}
