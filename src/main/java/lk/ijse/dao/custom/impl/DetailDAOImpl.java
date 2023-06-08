package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.entity.Detail;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailDAOImpl implements DetailDAO {
    @Override
    public  boolean save(Detail details) throws SQLException {
        return CrudUtil.execute("INSERT INTO grama_vista.detail ( function_name, user,time ,date,description) " +
                "VALUES (?,?,?,?,?)",details.getFunction_name(),details.getUser(),details.getTime(),details.getDate(),details.getDescription());
    }




    @Override
    public  List<Detail> search(Integer dayCount) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * , TIMESTAMPDIFF(day ,date,now()) AS days FROM grama_vista.detail HAVING days<?", dayCount);
        List<Detail> details = new ArrayList<>();
        while (resultSet.next()) {
            details.add(new Detail(resultSet.getString(2),resultSet.getString(3),
                    resultSet.getTime(4).toLocalTime(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6)));
        }
        return details;
    }


    @Override
    public  List<Detail> searchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *  FROM grama_vista.detail ");
        List<Detail> details = new ArrayList<>();
        while (resultSet.next()) {
            details.add(new Detail(resultSet.getString(2),resultSet.getString(3),
                    resultSet.getTime(4).toLocalTime(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6)));
        }
        return details;
    }
}
