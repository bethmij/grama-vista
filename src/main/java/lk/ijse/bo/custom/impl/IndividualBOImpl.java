package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.IndividualBO;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.ResidenceDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.ResidenceDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.List;

public class IndividualBOImpl implements IndividualBO {
    ResidenceDAO residenceDAO = new ResidenceDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();
    CivilDAO civilDAO = new CivilDAOImpl();

    public List<String> loadResidenceId() throws SQLException {
        return residenceDAO.loadResidenceID();
    }

    public String getCivilNextId() throws SQLException {
        return civilDAO.generateNewID();
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
