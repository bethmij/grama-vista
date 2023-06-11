package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MaternityRegistBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.MaternityDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.MaternityDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Maternity;

import java.sql.SQLException;
import java.util.List;

public class MaternityRegistBOImpl implements MaternityRegistBO {

    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    MaternityDAO maternityDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MATERNITYDAO);

    @Override
    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public Integer getNextMaternityID() throws SQLException {
        return maternityDAO.getNextId();
    }

    @Override
    public boolean saveMaternity(MaternityDTO maternityDTO) throws SQLException, ClassNotFoundException {
        Maternity maternity = new Maternity(maternityDTO.getReg(),maternityDTO.getCivil(), maternityDTO.getMonth(),maternityDTO.getMidWife());
        return maternityDAO.save(maternity);
    }

    @Override
    public boolean updateMaternity(MaternityDTO maternityDTO) throws SQLException, ClassNotFoundException {
        Maternity maternity = new Maternity(maternityDTO.getReg(),maternityDTO.getCivil(), maternityDTO.getMonth(),maternityDTO.getMidWife());
        return maternityDAO.update(maternity);
    }

    @Override
    public String searchCivilByID (String id) throws SQLException {
        return civilDAO.searchById(id);
    }


}
