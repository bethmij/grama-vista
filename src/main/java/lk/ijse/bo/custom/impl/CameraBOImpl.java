package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CameraBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;

import java.io.InputStream;
import java.sql.SQLException;

public class CameraBOImpl implements CameraBO {

    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);

    @Override
    public Integer getCivilId(String nic) throws SQLException {
        return civilDAO.getID(nic);
    }

    @Override
    public boolean uploadImage(String id, InputStream in) throws SQLException {
        return civilDAO.upload(id, in);
    }
}
