package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DisableRegistrationBO;
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
    DisableDAO disableDAO = new DisableDAOImpl();
    CivilDAO civilDAO = new CivilDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    public Integer getNextDisableId() throws SQLException {
        return disableDAO.getNextId();
    }

    public boolean saveDisable(DisableDTO disableDTO) throws SQLException {
        Disable disable = new Disable(disableDTO.getDisable(),disableDTO.getCivil(),disableDTO.getDisable(),disableDTO.getDesc());
        return disableDAO.save(disable);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateDisable(DisableDTO disableDTO) throws SQLException {
        Disable disable = new Disable(disableDTO.getDisable(),disableDTO.getCivil(),disableDTO.getDisable(),disableDTO.getDesc());
        return disableDAO.update(disable);
    }

    public String searchCivilByID (String id) throws SQLException {
        return civilDAO.searchById(id);
    }
}
