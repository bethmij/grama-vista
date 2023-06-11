package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SamurdhiBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ResidenceDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dao.custom.impl.ResidenceDAOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Residence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SamurdhiBOImpl implements SamurdhiBO {

    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    ResidenceDAO residenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESIDENCEDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public List<String> loadResidenceId() throws SQLException {
        return residenceDAO.loadResidenceID();
    }

    @Override
    public List<CivilDTO> getCivilDetail(String id) throws SQLException {
        List<Civil> civil = queryDAO. getCivil(id);
        List<CivilDTO> civilDTOS = new ArrayList<>();
        for (Civil civil1 : civil) {
            civilDTOS.add(new CivilDTO(civil1.getID(), civil1.getImage(), civil1.getName(), civil1.getNic(), civil1.getAddress(), civil1.getDob()
                    , civil1.getAge(), civil1.getGender(), civil1.getMarriage(), civil1.getRelation(), civil1.getEducation(), civil1.getSchool(), civil1.getOccupation()
                    , civil1.getWork(), civil1.getSalary(), civil1.getEmail()));
        }
        return civilDTOS;

    }

    @Override
    public ResidenceDTO searchResidence(String id) throws SQLException, ClassNotFoundException {
        Residence residence = residenceDAO.search(id);
        ResidenceDTO residenceDTO = new ResidenceDTO(residence.getHome_id(),residence.getDivision_id(),residence.getAddress(),residence.getHouse_holder_name(),
                residence.getMember_count(),residence.getCount_below_18(),residence.getResidence_type(),residence.getElectricity(),residence.getWater_supply());
        return residenceDTO;
    }
}
