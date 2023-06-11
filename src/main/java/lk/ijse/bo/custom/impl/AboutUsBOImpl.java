package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AboutUsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AboutUsBOImpl implements AboutUsBO {

    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    ResidenceDAO residenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESIDENCEDAO);
    LandDAO landDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<CivilDTO> searchCivil() throws SQLException {
        List<Civil> civilList = civilDAO.searchAll();
        List<CivilDTO> civilDTOList = new ArrayList<>();
        for (Civil civil : civilList) {
            civilDTOList.add(new CivilDTO(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                    , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                    , civil.getWork(), civil.getSalary(), civil.getEmail()));
        }
        return civilDTOList;
    }

    @Override
    public boolean saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        return detailDAO.save(detail1);
    }

    @Override
    public Integer getPopulation() throws SQLException {
        return divisionDAO.getPopulation();
    }

    @Override
    public Integer getMale() throws SQLException {
        return civilDAO.getMale();
    }

    @Override
    public Integer getFemale() throws SQLException {
        return civilDAO.getFemale();
    }

    @Override
    public Integer getResidenceCount() throws SQLException {
        return residenceDAO.getCount();
    }

    @Override
    public Integer getLandCount() throws SQLException {
        return landDAO.getCount();
    }

}
