package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.*;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public List<DeadEntity> searchAllDead() throws SQLException {
        List<DeadEntity> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT dead_people.*,TIMESTAMPDIFF(year,civil.dob,dead_date) AS Age, civil.name FROM grama_vista.dead_people JOIN grama_vista.civil  on civil.reg_number = dead_people.reg_number ");
        while (resultSet.next()) {

            datalist.add ( new DeadEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getDate(3).toLocalDate(),resultSet.getInt(4)));

        }
        return datalist;
    }


    @Override
    public DeadEntity searchDead(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT dead_people.*,TIMESTAMPDIFF(year,civil.dob,dead_date) AS Age, civil.name FROM grama_vista.dead_people JOIN grama_vista.civil  on civil.reg_number = dead_people.reg_number WHERE dead_people.id=?", id);
        if (resultSet.next()) {

            return new DeadEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getDate(3).toLocalDate(),resultSet.getInt(4));

        }
        return null;
    }


    @Override
    public List<ElecCandidateEntity> searchCandidate(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT ac.*, c.name, c.political_party FROM grama_vista.add_candidate ac JOIN grama_vista.candidate c on ac.candidate_id = c.elect_reg_num WHERE ac.election_id=?",id);
        List<ElecCandidateEntity> list = new ArrayList<>();
        while (resultSet.next())  {
            list.add(new ElecCandidateEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return list;
    }


    @Override
    public DisableEntity searchDisable(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT d.* , c.name FROM grama_vista.disable_people d JOIN grama_vista.civil c on c.reg_number = d.reg_number WHERE d.id=?", reg_id);
        if (resultSet.next()) {

            return new DisableEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getString(3),resultSet.getString(4));

        }
        return null;
    }


    @Override
    public List<DisableEntity> searchAllDisable() throws SQLException {
        List<DisableEntity> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT d.* , c.name FROM grama_vista.disable_people d JOIN grama_vista.civil c on c.reg_number = d.reg_number");
        while (resultSet.next()) {

            datalist.add ( new DisableEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(5),
                    resultSet.getString(3),resultSet.getString(4)));

        }
        return datalist;
    }


    @Override
    public List<LandDetailEntity> searchLandDetail(String id) throws SQLException {
        List<LandDetailEntity> landDetails = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT ld.* , lt.name FROM grama_vista.land_type lt JOIN grama_vista.land_detail ld on lt.type_id = ld.type_id WHERE ld.land_num=?",id);
        while (resultSet.next()){
            landDetails.add(new LandDetailEntity(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3) ));
        }
        return landDetails;
    }


    @Override
    public List<LandDetailEntity> searchAllLandDetail() throws SQLException {
        List<LandDetailEntity> landDetail = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT ld.* , lt.name FROM grama_vista.land_type lt JOIN grama_vista.land_detail ld on lt.type_id = ld.type_id ");
        while (resultSet.next()){
            landDetail.add(new LandDetailEntity(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3) ));
        }
        return landDetail;
    }


    @Override
    public MaternityEntity searchMaternity(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT m.*,((TIMESTAMPDIFF(month ,m.register_date,now()))+m.months) AS months, c.name FROM grama_vista.maternity_people m JOIN grama_vista.civil c on c.reg_number = m.reg_number WHERE m.id=?", id);
        if (resultSet.next()) {

            return new MaternityEntity(resultSet.getString(1), resultSet.getString(2),resultSet.getString(7),
                    resultSet.getInt(5), resultSet.getString(3));

        }
        return null;
    }


    @Override
    public List<MaternityEntity> searchAllMaternity() throws SQLException {
        List<MaternityEntity> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT m.*,((TIMESTAMPDIFF(month ,m.register_date,now()))+m.months) AS months, c.name FROM grama_vista.maternity_people m JOIN grama_vista.civil c on c.reg_number = m.reg_number");
        while (resultSet.next()) {

            datalist.add (new MaternityEntity(resultSet.getString(1), resultSet.getString(2),resultSet.getString(7),
                    resultSet.getInt(6), resultSet.getString(3)));

        }
        return datalist;
    }


    @Override
    public  List<Civil> getCivil(String home_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT DISTINCT c.* , TIMESTAMPDIFF(year,c.dob,now()) AS Age FROM grama_vista.civil c JOIN grama_vista.multi_residence m on c.reg_number = m.reg_number WHERE m.home_id=? AND c.isArchieved=FALSE",home_id);
        List<Civil> datalist = new ArrayList<>();
        while (resultSet.next()) {

            datalist.add( new Civil(resultSet.getString(1), resultSet.getBlob(14),resultSet.getString(3),resultSet.getString(2), resultSet.getString(4),
                    resultSet.getDate(6).toLocalDate(), resultSet.getInt(19),resultSet.getString(5),resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9),resultSet.getString(10), resultSet.getString(11),resultSet.getString(12),resultSet.getDouble(13),resultSet.getString(15)));

        }
        return datalist;
    }


    @Override
    public  String getDivisionId(Integer civil_id) throws SQLException {
        String sql = "SELECT gn_division.division_id FROM grama_vista.residence JOIN grama_vista.multi_residence ON residence.home_id = multi_residence.home_id JOIN grama_vista.gn_division ON residence.division_id = gn_division.division_id WHERE multi_residence.reg_number=?";
        ResultSet resultSet = CrudUtil.execute(sql,civil_id);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }


    @Override
    public  String getCandidateName(Integer winner_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT c.name FROM grama_vista.vote v JOIN grama_vista.candidate c on v.candidate_id = c.elect_reg_num WHERE c.elect_reg_num=?", winner_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

}
