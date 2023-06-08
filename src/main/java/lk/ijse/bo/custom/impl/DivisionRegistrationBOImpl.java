package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DivisionRegistrationBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DivisionDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Division;

import java.sql.SQLException;

public class DivisionRegistrationBOImpl implements DivisionRegistrationBO {
    DivisionDAO divisionDAO = new DivisionDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    public String getNextDivisionID() throws SQLException {
        return divisionDAO.getNextOrderId();
    }

    public boolean saveDivision(DivisionDTO divisionDTO) throws SQLException {
        Division division = new Division(divisionDTO.getDivision_id(),divisionDTO.getName(),divisionDTO.getDiv_Secretariat(),divisionDTO.getAdmin_officer()
                ,divisionDTO.getLand_area(),divisionDTO.getPopulation());
        return divisionDAO.save(division);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateDivision(DivisionDTO divisionDTO) throws SQLException {
        Division division = new Division(divisionDTO.getDivision_id(),divisionDTO.getName(),divisionDTO.getDiv_Secretariat(),divisionDTO.getAdmin_officer()
                ,divisionDTO.getLand_area(),divisionDTO.getPopulation());
        return divisionDAO.update(division);
    }
}
