package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.ContactDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MultiResidenceDTO;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface IndividualFormBO extends SuperBO {

    void saveDetail(DetailDTO detail) throws SQLException;

    String getDivisionId(Integer id) throws SQLException;

    boolean saveCivil(CivilDTO civil) throws SQLException;

    boolean updateCivil(CivilDTO civil) throws SQLException;

    Integer getCivilId(String nic ) throws SQLException;

    boolean uploadImage(String id, InputStream in) throws SQLException;


}
