package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.Dead;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.Maternity;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

            boolean isDeadSaved = CrudUtil.execute("INSERT INTO grama_vista.dead_people (reg_number, dead_date) VALUES (?,?)",
                    dead.getCivil_ID(),  dead.getDate());
            if (isDeadSaved) {
                boolean isPopulationUpdate = DivisionModel.UpdateDeadPopulation(division_id);
                if (isPopulationUpdate) {
                    boolean isRemove = CivilModel.delete(dead.getCivil_ID());
                    if(isRemove) {
                        con.commit();
                    }return true;
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


    public static List<String> loadDeadId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM grama_vista.dead_people ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }


    public static List<DeadDTO> searchAll() throws SQLException {
        List<DeadDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT dead_people.*,TIMESTAMPDIFF(year,civil.dob,dead_date) AS Age, civil.name FROM grama_vista.dead_people JOIN grama_vista.civil  on civil.reg_number = dead_people.reg_number ");
        while (resultSet.next()) {

            datalist.add ( new DeadDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getDate(3).toLocalDate(),resultSet.getInt(4)));

        }
        return datalist;
    }

    public static DeadDTO search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT dead_people.*,TIMESTAMPDIFF(year,civil.dob,dead_date) AS Age, civil.name FROM grama_vista.dead_people JOIN grama_vista.civil  on civil.reg_number = dead_people.reg_number WHERE dead_people.id=?", id);
        if (resultSet.next()) {

            return new DeadDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getDate(3).toLocalDate(),resultSet.getInt(4));

        }
        return null;
    }

    public static boolean update(Dead dead) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.dead_people SET reg_number=?, dead_date=? WHERE id=?",
                    dead.getCivil_ID(),  dead.getDate(),dead.getReg_id());



    }

    public static boolean dead(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.dead_people WHERE id=?", id);
    }
}
