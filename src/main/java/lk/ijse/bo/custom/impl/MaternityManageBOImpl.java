package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MaternityManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.MaternityDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.MaternityDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.MaternityEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaternityManageBOImpl implements MaternityManageBO {

    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    MaternityDAO maternityDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MATERNITYDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public List<String> loadMaternityID() throws SQLException {
        return maternityDAO.loadMaternityID();
    }

    @Override
    public List<MaternityDTO> searchAllMaternity() throws SQLException {
        List<MaternityEntity> maternity  = queryDAO.searchAllMaternity();
        List<MaternityDTO> maternityDTOS = new ArrayList<>();
        for (MaternityEntity maternityEntity : maternity) {
            maternityDTOS.add(new MaternityDTO(maternityEntity.getReg(),maternityEntity.getCivil(),maternityEntity.getName(),
                    maternityEntity.getMonth(),maternityEntity.getMidWife()));
        }
        return maternityDTOS;
    }

    @Override
    public MaternityDTO searchMaternity(String id) throws SQLException {
        MaternityEntity maternityEntity = queryDAO.searchMaternity(id);
        MaternityDTO maternityDTO = new MaternityDTO(maternityEntity.getReg(),maternityEntity.getCivil(),maternityEntity.getName(),
                maternityEntity.getMonth(),maternityEntity.getMidWife());
        return maternityDTO;
    }

    @Override
    public boolean deleteMaternity(String id) throws SQLException, ClassNotFoundException {
        return maternityDAO.delete(id);
    }


}
