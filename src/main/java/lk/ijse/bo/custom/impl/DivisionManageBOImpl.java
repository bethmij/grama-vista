package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DivisionManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DivisionDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Division;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionManageBOImpl implements DivisionManageBO {

    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<String> loadDivision() throws SQLException {
        return divisionDAO.loadDivisionID();
    }

    @Override
    public List<DivisionDTO> searchAllDivision() throws SQLException {
        List<Division> division  = divisionDAO.searchAll();
        List<DivisionDTO> divisionDTOS = new ArrayList<>();
        for (Division division1 : division) {
            divisionDTOS.add(new DivisionDTO(division1.getDivision_id(),division1.getName(),division1.getDiv_Secretariat(),division1.getAdmin_officer()
            ,division1.getPopulation(),division1.getLand_area()));
        }
        return divisionDTOS;
    }

    @Override
    public DivisionDTO searchDivision(String id) throws SQLException, ClassNotFoundException {
        Division division = divisionDAO.search(id);
        DivisionDTO divisionDTO = new DivisionDTO(division.getDivision_id(),division.getName(),division.getDiv_Secretariat(),division.getAdmin_officer()
                ,division.getPopulation(),division.getLand_area());
        return divisionDTO;
    }

    @Override
    public boolean deleteDivision(String id) throws SQLException, ClassNotFoundException {
        return divisionDAO.delete(id);
    }

    @Override
    public String getDivisionName(String id) throws SQLException {
        return divisionDAO.getName(id);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
