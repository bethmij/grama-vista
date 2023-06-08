package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface VoteResultBO {

    Integer getCandidateCount() throws SQLException;

    Integer getCivilCount() throws SQLException;

    Integer getVoteCount() throws SQLException;

    Integer getVote(Integer id) throws SQLException;

    String getWinnerName(Integer id) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

}
