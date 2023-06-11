package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AddLandTypeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.LandTypeDAO;
import lk.ijse.dao.custom.impl.LandTypeDAOImpl;

import java.sql.SQLException;

public class AddLandTypeBOImpl implements AddLandTypeBO {

    LandTypeDAO typeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDTYPEDAO);

    @Override
    public Integer getLandTypeID(String id) throws SQLException {
        return typeDAO.getTypeId(id);
    }
}
