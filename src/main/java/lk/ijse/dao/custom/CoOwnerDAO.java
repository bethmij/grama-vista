package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.CoOwner;

import java.sql.SQLException;
import java.util.List;

public interface CoOwnerDAO extends CrudDAO<CoOwner,String> {

    boolean save(List<CoOwner> coOwnerTypeList)  throws SQLException;

    List<CoOwner> searchOwner(String id) throws SQLException;

    boolean update(List<CoOwner> coOwnerLists) throws SQLException;

}
