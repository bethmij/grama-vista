package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DisableManageBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DisableDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DisableDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.DisableEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisableManageBOImpl implements DisableManageBO {
    DisableDAO disableDAO = new DisableDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    public List<String> loadDisableId() throws SQLException {
         return disableDAO.loadDisableId();
    }

    public List<DisableDTO> searchAllDisable() throws SQLException {
        List<DisableEntity> disable  = queryDAO.searchAllDisable();
        List<DisableDTO> disableDTOS = new ArrayList<>();
        for (DisableEntity disableEntity : disable) {
            disableDTOS.add(new DisableDTO(disableEntity.getId(),disableEntity.getCivil(),disableEntity.getName()
            ,disableEntity.getDisable(),disableEntity.getDesc()));
        }
        return disableDTOS;
    }

    public DisableDTO searchDisable(String id) throws SQLException {
        DisableEntity disable = queryDAO.searchDisable(id);
        DisableDTO disableDTO = new DisableDTO(disable.getId(),disable.getCivil(),disable.getName()
                ,disable.getDisable(),disable.getDesc());
        return disableDTO;
    }

    public boolean deleteDisable(String id) throws SQLException {
        return disableDAO.delete(id);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
