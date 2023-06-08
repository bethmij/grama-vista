package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MultiResidenceDAO;
import lk.ijse.entity.MultiResidence;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultiResidenceDAOImpl implements MultiResidenceDAO {

    @Override
    public  boolean save(List<MultiResidence> multiResidenceList) throws SQLException {
        for(MultiResidence list : multiResidenceList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    public boolean save(MultiResidence multiResidence) throws SQLException {
        String sql = "INSERT INTO grama_vista.multi_residence (home_id, reg_number) " +
                "VALUES (?,?)";

        return CrudUtil.execute(sql, multiResidence.getResidence_id(), multiResidence.getCivil_id());
    }

    @Override
    public  boolean update(List<MultiResidence> multiResidenceList) throws SQLException {
        for(MultiResidence list : multiResidenceList) {
            if(!update(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public  boolean update(MultiResidence residenceList) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.multi_residence SET reg_number=? WHERE home_id=?" ,residenceList.getCivil_id(),residenceList.getResidence_id());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MultiResidence search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<MultiResidence> searchAll() throws SQLException {
        return null;
    }

    @Override
    public  List<MultiResidence> searchResidence(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.multi_residence WHERE reg_number=?",reg_id);
        List<MultiResidence> residenceList = new ArrayList<>();
        while (resultSet.next()){
            residenceList.add(new MultiResidence(resultSet.getString(1),resultSet.getString(2)));
        }
        return residenceList;
    }
}
