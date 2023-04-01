package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Dead;
import lk.ijse.dto.Maternity;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadModel {
    public static Integer getNextId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.dead_people ORDER BY id DESC LIMIT 1");

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return (id+1);
        }
        return 1;
    }


    public static boolean save(Dead dead,String division_id) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);


            boolean isDeadSaved = CrudUtil.execute("INSERT INTO grama_vista.dead_people (reg_number, dead_date) VALUES (?,?,?)",
                    dead.getCivil_ID(),  dead.getDate());
            if (isDeadSaved) {
                boolean isPopulationUpdate = DivisionModel.UpdateDeadPopulation(division_id);
                if (isPopulationUpdate) {
                    con.commit();
                    return true;
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






}
