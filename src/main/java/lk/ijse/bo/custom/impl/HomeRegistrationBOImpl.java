package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.HomeRegistrationBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.dao.custom.ResidenceDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DivisionDAOImpl;
import lk.ijse.dao.custom.impl.ResidenceDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Residence;

import java.sql.SQLException;
import java.util.List;

public class HomeRegistrationBOImpl implements HomeRegistrationBO {
    DivisionDAO divisionDAO = new DivisionDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();
    ResidenceDAO residenceDAO = new ResidenceDAOImpl();

    public List<String> loadDivisionId() throws SQLException {
        return divisionDAO.loadDivisionID();
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateResidence(ResidenceDTO residenceDTO) throws SQLException, ClassNotFoundException {
        Residence residence = new Residence(residenceDTO.getID(),residenceDTO.getDivision(),residenceDTO.getAddress(),residenceDTO.getHolder(),residenceDTO.getMember(),
                residenceDTO.getBelow(),residenceDTO.getType(),residenceDTO.getElec(),residenceDTO.getWater());
        return residenceDAO.update(residence);
    }

    public boolean saveResidence(ResidenceDTO residenceDTO) throws SQLException {
        Residence residence = new Residence(residenceDTO.getID(),residenceDTO.getDivision(),residenceDTO.getAddress(),residenceDTO.getHolder(),residenceDTO.getMember(),
                residenceDTO.getBelow(),residenceDTO.getType(),residenceDTO.getElec(),residenceDTO.getWater());
        return residenceDAO.save(residence);
    }
}
