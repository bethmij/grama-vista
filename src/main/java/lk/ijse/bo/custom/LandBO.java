package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface LandBO extends SuperBO {

    Integer getNextLandID() throws SQLException;

    Integer getLandType(String id) throws SQLException;

    boolean saveLand(LandDTO landDTO) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateLand(LandDTO landDTO) throws SQLException;
}
