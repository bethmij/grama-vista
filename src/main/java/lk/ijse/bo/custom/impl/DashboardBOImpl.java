package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashboardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;

import java.sql.SQLException;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {

    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);

    @Override
    public Map<Integer, Integer> getDateDiff() throws SQLException {
        return  civilDAO.getDateDiff();
    }

    @Override
    public boolean sentMail(Integer key) throws SQLException {
        return civilDAO.isMailSent(key);
    }

    @Override
    public String getEmail(Integer id) throws SQLException {
        return civilDAO.getEmail(id);
    }

    @Override
    public String getCivilName(String id) throws SQLException {
        return civilDAO.getName(id);
    }

    @Override
    public void updateEmail(Integer id, String to) throws SQLException {
        civilDAO.updateEmail(id,to);
    }
}
