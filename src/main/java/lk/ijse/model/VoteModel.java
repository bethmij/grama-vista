package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.AddCandidate;
import lk.ijse.dto.ElecCandidate;
import lk.ijse.dto.Vote;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteModel {

    public static boolean save(Vote vote, List<AddCandidate> addCandidateList) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.votereg (election_id, election_type, candidate_count,election_date) VALUES (?,?,?,?)",
                    vote.getElection_id(), vote.getElection_type(), vote.getCandidate_count(), vote.getDate());
            if (isSaved) {
                boolean isAddCandidate = AddCandidateModel.save(addCandidateList);
                if (isAddCandidate) {
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

    public static List<String> getElecID(String civil_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT election_id FROM grama_vista.vote WHERE civil_id=? ",civil_id);
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }

    public static List<String> getElecID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT election_id FROM grama_vista.votereg ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }

    public static boolean saveVote(String election_id, Integer candidate_id, Integer civilID) throws SQLException {


        return  CrudUtil.execute("INSERT INTO grama_vista.vote (election_id, civil_id, candidate_id, voted_date) VALUES (?,?,?,?)",
                election_id,civilID,candidate_id, LocalDate.now());

    }

    public static Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(civil_id) FROM grama_vista.vote ");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    public static Integer getVotefor1(Integer id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(civil_id) FROM grama_vista.vote WHERE candidate_id=?",id);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    public static String getName(Integer winner_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT c.name FROM grama_vista.vote v JOIN grama_vista.candidate c on v.candidate_id = c.elect_reg_num WHERE c.elect_reg_num=?", winner_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static Vote search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.votereg WHERE election_id=?",id);
        if(resultSet.next()){
            return new Vote(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4).toLocalDate());
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.votereg WHERE election_id=?", id);
    }

    public static List<Vote> searchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.votereg ");
        List<Vote> voteList = new ArrayList<>();
        while (resultSet.next()){
             voteList.add(new Vote(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4).toLocalDate()));
        }
        return voteList;
    }

    public static boolean update(Vote vote) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.votereg SET election_type=?, candidate_count=?, election_date=? WHERE election_id=?",
                vote.getElection_type(), vote.getCandidate_count(), vote.getDate(), vote.getElection_id());
    }

    public static List<ElecCandidate> searchCandidate(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT ac.*, c.name, c.political_party FROM grama_vista.add_candidate ac JOIN grama_vista.candidate c on ac.candidate_id = c.elect_reg_num WHERE ac.election_id=?",id);
        List<ElecCandidate> list = new ArrayList<>();
        while (resultSet.next())  {
            list.add(new ElecCandidate(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return list;
    }

    public static boolean deleteCandidate(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.add_candidate WHERE election_id=?", id);
    }

    public static boolean updateCount(String election_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.votereg SET candidate_count=candidate_count+1 WHERE election_id=?",election_id);
    }
}
