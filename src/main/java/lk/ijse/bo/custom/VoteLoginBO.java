package lk.ijse.bo.custom;

import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.VoteDTO;

import java.sql.SQLException;
import java.util.List;

public interface VoteLoginBO {

    VoteDTO searchVote(String id) throws SQLException, ClassNotFoundException;

    CivilDTO searchByCivilNIC(String nic) throws SQLException;

    List<String> getElectionIDbyID(String id) throws SQLException;

    List<String> getElectionID() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
