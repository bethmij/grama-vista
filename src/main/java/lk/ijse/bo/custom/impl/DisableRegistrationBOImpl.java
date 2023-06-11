package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DisableRegistrationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DisableDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DisableDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Disable;

import java.sql.SQLException;
import java.util.List;

public class DisableRegistrationBOImpl implements DisableRegistrationBO {

    DisableDAO disableDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DISABLEDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    @Override
    public Integer getNextDisableId() throws SQLException {
        return disableDAO.getNextId();
    }

    @Override
    public boolean saveDisable(DisableDTO disableDTO) throws SQLException, ClassNotFoundException {
        Disable disable = new Disable(disableDTO.getId(),disableDTO.getCivil(),disableDTO.getDisable(),disableDTO.getDesc());
        return disableDAO.save(disable);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public boolean updateDisable(DisableDTO disableDTO) throws SQLException, ClassNotFoundException {
        Disable disable = new Disable(disableDTO.getId(),disableDTO.getCivil(),disableDTO.getDisable(),disableDTO.getDesc());
        return disableDAO.update(disable);
    }

    @Override
    public String searchCivilByID (String id) throws SQLException {
        return civilDAO.searchById(id);
    }
}
