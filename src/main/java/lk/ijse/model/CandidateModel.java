package lk.ijse.model;

import javafx.scene.image.Image;
import lk.ijse.dto.Candidate;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.DivisionDTO;
import lk.ijse.dto.tm.CandidateTM;
import lk.ijse.util.CrudUtil;

import java.awt.*;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CandidateModel {
    public static boolean save(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO grama_vista.candidate ( elect_reg_num,division_id, nic, name,address, contact, political_party) " +
                "VALUES (?,?,?,?,?,?,?)";


        return  CrudUtil.execute(sql, candidate.getElection_id(), candidate.getDivision_id(), candidate.getNic(), candidate.getName(),
                candidate.getAddress(), candidate.getContact(), candidate.getPolitical_party());

    }

    public static boolean upload (String id,InputStream in ) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.candidate SET image=? WHERE elect_reg_num=?",in,id);
    }

    public static CandidateDTO search(String election_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.candidate WHERE elect_reg_num=?", election_id);
        if (resultSet.next()) {

            return new CandidateDTO(resultSet.getString(1), resultSet.getBlob(9), resultSet.getString(4),
                    resultSet.getString(3), resultSet.getString(2),resultSet.getString(5),resultSet.getInt(6), resultSet.getString(7));

        }
        return null;
    }

    public static List<String> loadElectionID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT elect_reg_num FROM grama_vista.candidate ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()) {
            id.add(resultSet.getString(1));
        }

        return id;
    }

    public static List<CandidateDTO> searchAll() throws SQLException {
        List<CandidateDTO> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.candidate ");
        while (resultSet.next()) {

            datalist.add((new CandidateDTO(resultSet.getString(1), resultSet.getBlob(9), resultSet.getString(4),
                    resultSet.getString(3), resultSet.getString(2),resultSet.getString(5),resultSet.getInt(6), resultSet.getString(7))));
            ;

        }
        return datalist;
    }

    public static boolean update(Candidate candidate) throws SQLException {
        String sql = "UPDATE grama_vista.candidate SET division_id=?, nic=?, name=?,address=?, contact=?, political_party=? " +
                "WHERE elect_reg_num=?";


        return  CrudUtil.execute(sql, candidate.getDivision_id(), candidate.getNic(), candidate.getName(),
                candidate.getAddress(), candidate.getContact(), candidate.getPolitical_party(), candidate.getElection_id());

    }

    public static String getName(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.candidate WHERE elect_reg_num=?", reg_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    public static boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.candidate WHERE elect_reg_num=?", id);
    }
}
