package lk.ijse.bo.custom;

import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface LandBO {

    Integer getNextLandID() throws SQLException;

    Integer getLandType(String id) throws SQLException;

    boolean saveLand(LandDTO landDTO , List<LandDetailDTO> landDetailList, List<CoOwnerDTO> coOwnerLists) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateLand(LandDTO landDTO , List<LandDetailDTO> landDetailList, List<CoOwnerDTO> coOwnerLists) throws SQLException;
}
