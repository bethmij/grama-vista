package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CivilViewBO;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;

import java.sql.SQLException;

public class CivilViewBOImpl implements CivilViewBO {
    CivilDAO civilDAO = new CivilDAOImpl();

    @Override
    public boolean deleteCivil(String id) throws SQLException, ClassNotFoundException {
        return civilDAO.delete(id);
    }
}
