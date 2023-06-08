package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DeadPeopleBO;
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
    DeadDAO deadDAO = new DeadDAOImpl();
    CivilDAO civilDAO = new CivilDAOImpl();
    DivisionDAO divisionDAO = new DivisionDAOImpl();
    QueryDAO queryDAO =  new QueryDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    public Integer getNextDeadId() throws SQLException {
        return deadDAO.getNextId();
    }

    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    public String getDivisionId(Integer id) throws SQLException {
        return queryDAO.getDivisionId(id);
    }

    public boolean saveDead(DeadDTO deadDTO, String division_id) throws SQLException, ClassNotFoundException {
        Dead dead = new Dead(deadDTO.getDead_id(),deadDTO.getCivil_id(),deadDTO.getDate());

            Connection con = null;
            try {
                con = DBConnection.getInstance().getConnection();
                con.setAutoCommit(false);

                boolean isDeadSaved = CrudUtil.execute("INSERT INTO grama_vista.dead_people (reg_number, dead_date) VALUES (?,?)",
                        dead.getCivil_ID(),  dead.getDate());
                if (isDeadSaved) {
                    boolean isPopulationUpdate = divisionDAO.UpdateDeadPopulation(division_id);
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

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateDead(DeadDTO deadDTO) throws SQLException, ClassNotFoundException {
        Dead dead = new Dead(deadDTO.getDead_id(),deadDTO.getCivil_id(),deadDTO.getDate());
        return deadDAO.update(dead);
    }

    public String searchCivilByID (String id) throws SQLException {
        return civilDAO.searchById(id);
    }
}
