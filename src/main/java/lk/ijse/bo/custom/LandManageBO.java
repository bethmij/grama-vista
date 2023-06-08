package lk.ijse.bo.custom;

import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface LandManageBO {
    void saveDetail(DetailDTO detail) throws SQLException;

    List<String> loadLandId() throws SQLException;

    List<LandDTO> searchAllLand() throws SQLException;

    List<LandDetailDTO> searchAllLandDetail() throws SQLException;

    List<CoOwnerDTO> searchAllOwners() throws SQLException;

    LandDTO searchLand(String id) throws SQLException;

    List<LandDetailDTO> searchLandDetail(String id) throws SQLException;

    List<CoOwnerDTO> searchOwners(String id) throws SQLException, ClassNotFoundException;

    boolean deleteLand(String id) throws SQLException;
}
