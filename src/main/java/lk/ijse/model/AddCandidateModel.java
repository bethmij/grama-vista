package lk.ijse.model;

import lk.ijse.dto.AddCandidate;
import lk.ijse.dto.Contact;
import lk.ijse.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class AddCandidateModel {

    public static boolean save(List<AddCandidate> addCandidateList ) throws SQLException {
        for(AddCandidate list : addCandidateList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save( AddCandidate addCandidate ) throws SQLException {

        return CrudUtil.execute("INSERT INTO grama_vista.add_candidate (election_id, candidate_id) VALUES (?,?)",
                addCandidate.getElection_id(),addCandidate.getCandidate_id());
    }
}
