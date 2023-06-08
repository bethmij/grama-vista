package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OwnershipBO;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class OwnershipBOImpl implements OwnershipBO {
    CivilDAO civilDAO = new CivilDAOImpl();

    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }
}
