package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AddResidenceBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ResidenceDAO;
import lk.ijse.dao.custom.impl.ResidenceDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class AddResidenceBOImpl implements AddResidenceBO {

    ResidenceDAO residenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESIDENCEDAO);

    @Override
    public List<String> loadResidenceID() throws SQLException {
        return residenceDAO.loadResidenceID();
    }
}
