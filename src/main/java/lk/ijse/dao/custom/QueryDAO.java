package lk.ijse.dao.custom;

import lk.ijse.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO {
    List<DeadEntity> searchAllDead() throws SQLException;

    DeadEntity searchDead(String id) throws SQLException;

    List<ElecCandidateEntity> searchCandidate(String id) throws SQLException;

    DisableEntity searchDisable(String reg_id) throws SQLException;

    List<DisableEntity> searchAllDisable() throws SQLException;

    List<LandDetailEntity> searchLandDetail(String id) throws SQLException;

    List<LandDetailEntity> searchAllLandDetail() throws SQLException;

    MaternityEntity searchMaternity(String id) throws SQLException;

    List<MaternityEntity> searchAllMaternity() throws SQLException;

    List<Civil> getCivil(String home_id) throws SQLException;

    String getDivisionId(Integer civil_id) throws SQLException;

    String getCandidateName(Integer winner_id) throws SQLException;
}
