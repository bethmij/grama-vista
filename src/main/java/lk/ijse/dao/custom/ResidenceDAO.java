package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Residence;
import lk.ijse.dto.ResidenceDTO;

import java.sql.SQLException;
import java.util.List;

public interface ResidenceDAO extends CrudDAO<Residence,String> {

    List<String> loadResidenceID() throws SQLException;

    Integer getCount() throws SQLException;

    String getDivisionId(String residence) throws SQLException;
}
