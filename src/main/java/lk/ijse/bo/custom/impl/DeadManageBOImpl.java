package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DeadManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DeadDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.DeadDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.DeadEntity;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeadManageBOImpl implements DeadManageBO {

    DeadDAO deadDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEADDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<DeadDTO> searchAllDead() throws SQLException {
        List<DeadEntity> dead  = queryDAO.searchAllDead();
        List<DeadDTO> deadDTO = new ArrayList<>();
        for (DeadEntity dead1 : dead) {
            deadDTO.add(new DeadDTO(dead1.getDead_id(),dead1.getCivil_id(), dead1.getName(), dead1.getDate(),dead1.getAge()));
        }
        return deadDTO;
    }

    @Override
    public DeadDTO searchDeath(String id) throws SQLException {
        DeadEntity deadEntity = queryDAO.searchDead(id);
        DeadDTO deadDTO = new DeadDTO(deadEntity.getDead_id(),deadEntity.getCivil_id(),deadEntity.getName(),deadEntity.getDate(),deadEntity.getAge());
        return deadDTO;
    }

    @Override
    public boolean deleteDead(String id) throws SQLException, ClassNotFoundException {
        return deadDAO.delete(id);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public List<String> loadDeadId() throws SQLException {
         return deadDAO.loadDeadId();
    }
}
