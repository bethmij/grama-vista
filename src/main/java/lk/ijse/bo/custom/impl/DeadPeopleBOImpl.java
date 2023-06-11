package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DeadPeopleBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dao.custom.impl.util.CrudUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Dead;
import lk.ijse.entity.Detail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeadPeopleBOImpl implements DeadPeopleBO {

    DeadDAO deadDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEADDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);
    QueryDAO queryDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public Integer getNextDeadId() throws SQLException {
        return deadDAO.getNextId();
    }

    @Override
    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    @Override
    public String getDivisionId(Integer id) throws SQLException {
        return queryDAO.getDivisionId(id);
    }

    @Override
    public boolean saveDead(DeadDTO deadDTO) throws SQLException, ClassNotFoundException {
        Dead dead = new Dead(deadDTO.getDead_id(),deadDTO.getCivil_id(),deadDTO.getDate());

            Connection con = null;
            try {
                con = DBConnection.getInstance().getConnection();
                con.setAutoCommit(false);

                boolean isDeadSaved = CrudUtil.execute("INSERT INTO grama_vista.dead_people (reg_number, dead_date) VALUES (?,?)",
                        dead.getCivil_ID(),  dead.getDate());
                if (isDeadSaved) {
                    boolean isPopulationUpdate = divisionDAO.UpdateDeadPopulation(deadDTO.getDivision_id());
                    if (isPopulationUpdate) {
                        boolean isRemove = civilDAO.delete(dead.getCivil_ID());
                        if(isRemove) {
                            con.commit();
                            return true;
                        }
                    }
                }

                return false;
            } catch (SQLException er) {
                System.out.println(er);
                con.rollback();
                return false;
            } finally {
                con.setAutoCommit(true);
            }



    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public boolean updateDead(DeadDTO deadDTO) throws SQLException, ClassNotFoundException {
        Dead dead = new Dead(deadDTO.getDead_id(),deadDTO.getCivil_id(),deadDTO.getDate());
        return deadDAO.update(dead);
    }

    @Override
    public String searchCivilByID (String id) throws SQLException {
        return civilDAO.searchById(id);
    }
}
