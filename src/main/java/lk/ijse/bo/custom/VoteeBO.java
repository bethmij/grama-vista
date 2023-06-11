package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface VoteeBO extends SuperBO {
    void saveDetail(DetailDTO detail) throws SQLException;

    boolean saveVote(String election_id, Integer candidate_id , Integer civilID) throws SQLException;
}
