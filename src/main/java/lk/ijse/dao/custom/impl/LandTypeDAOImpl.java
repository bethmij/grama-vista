package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.LandTypeDAO;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandTypeDAOImpl implements LandTypeDAO {

    @Override
    public  Integer getTypeId(String landType) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT type_id FROM grama_vista.land_type WHERE name=?",landType);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }





}
