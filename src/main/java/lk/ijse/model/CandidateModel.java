package lk.ijse.model;

import lk.ijse.dto.Candidate;
import lk.ijse.util.CrudUtil;

import java.io.InputStream;
import java.sql.SQLException;

public class CandidateModel {
    public static boolean save(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO grama_vista.candidate ( elect_reg_num,division_id, nic, name,address, contact, political_party) " +
                "VALUES (?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql,
                candidate.getElection_id(),
                candidate.getDivision_id(),
                candidate.getNic(),
                candidate.getName(),
                candidate.getAddress(),
                candidate.getContact(),
                candidate.getPolitical_party());

        return isSaved;

    }

    public static boolean upload (String id,InputStream in ) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.candidate SET image=? WHERE elect_reg_num=?",in,id);
    }
}
