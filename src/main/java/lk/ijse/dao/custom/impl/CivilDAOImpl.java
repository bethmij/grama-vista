package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CivilDAO;

import lk.ijse.entity.Civil;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CivilDAOImpl implements CivilDAO {

    @Override
    public  String generateNewID() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT reg_number FROM grama_vista.civil ORDER BY reg_number DESC LIMIT 1" );

        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            return String.valueOf(id+1);
        }
        return null;
    }




    @Override
    public  boolean save(Civil civil) throws SQLException {

        String sql = "INSERT INTO grama_vista.civil (nic, name, address, gender, dob, marriage_status, relation, education_status, school, occupation, working_address, salary,email,register_date) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        boolean isSaved = CrudUtil.execute(sql,
                civil.getNic(),civil.getName(),civil.getAddress(),civil.getGender(),civil.getDob(),civil.getMarriage(),civil.getRelation(),
                civil.getEducation(),civil.getSchool(),civil.getOccupation(),civil.getWork(),civil.getSalary(),civil.getEmail(), LocalDate.now());

        return isSaved;


    }

    @Override
    public  boolean upload(String id, InputStream in) throws SQLException {
        boolean isUploaded = CrudUtil.execute("UPDATE grama_vista.civil SET image=? WHERE reg_number=?",in,id);
        return isUploaded;
    }

    @Override
    public  List<String> loadCivilId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT reg_number FROM grama_vista.civil WHERE isArchieved=FALSE ORDER BY reg_number ASC ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    @Override
    public  String searchById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.civil WHERE reg_number = ? ", id);
        if(resultSet.next()){
            String name = resultSet.getString(1);
            return name;
        }
        return null;
    }







    @Override
    public  String getName(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.civil WHERE reg_number=?", reg_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    @Override
    public  Civil search(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year,dob,now()) AS Age FROM grama_vista.civil  WHERE reg_number=?", reg_id);
        if (resultSet.next()) {
            return new Civil(resultSet.getString(1), resultSet.getBlob(14),resultSet.getString(3),resultSet.getString(2), resultSet.getString(4),
                    resultSet.getDate(6).toLocalDate(), resultSet.getInt(19),resultSet.getString(5),resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9),resultSet.getString(10), resultSet.getString(11),resultSet.getString(12),resultSet.getDouble(13),resultSet.getString(15));

        }
        return null;
    }





    @Override
    public  List<Civil> searchAll() throws SQLException {

        List<Civil> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year,dob,now()) AS Age FROM grama_vista.civil WHERE isArchieved=FALSE ");
        while (resultSet.next()) {

            datalist.add (new Civil(resultSet.getString(1), resultSet.getBlob(14),resultSet.getString(3),resultSet.getString(2), resultSet.getString(4),
                    resultSet.getDate(6).toLocalDate(), resultSet.getInt(19), resultSet.getString(5), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                    resultSet.getString(10), resultSet.getString(11),resultSet.getString(12),resultSet.getDouble(13),resultSet.getString(15)));

        }
        return datalist;
    }

    @Override
    public  boolean update(Civil civil) throws SQLException {
        String sql = "UPDATE grama_vista.civil SET nic=?, name=?, address=?, gender=?, dob=?, marriage_status=?, relation=?," +
                " education_status=?, school=?, occupation=?, working_address=?, salary=?, email=? WHERE reg_number=?";

        return CrudUtil.execute(sql, civil.getNic(),civil.getName(),civil.getAddress(),civil.getGender(),civil.getDob(),civil.getMarriage(),civil.getRelation(),
                civil.getEducation(),civil.getSchool(),civil.getOccupation(),civil.getWork(),civil.getSalary(),civil.getEmail(),civil.getID());


    }







    @Override
    public  Map<Integer,Integer> getDateDiff() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT reg_number,TIMESTAMPDIFF(day,register_date,now()) AS date FROM grama_vista.civil WHERE isArchieved=FALSE ");
        Map<Integer,Integer> datalist = new HashMap<>();
        while (resultSet.next()) {
            datalist.put(resultSet.getInt(1),resultSet.getInt(2));
        }
        return datalist;
    }

    @Override
    public  String getEmail(Integer id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT email FROM grama_vista.civil WHERE reg_number=?",id);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public  Integer getMale() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(reg_number) FROM grama_vista.civil WHERE gender='Male' AND isArchieved=FALSE");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  Integer getFemale() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(reg_number) FROM grama_vista.civil WHERE gender='Female' AND isArchieved=FALSE");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  List<String> loadElectCivilId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT reg_number , TIMESTAMPDIFF(year,dob,now()) AS age FROM grama_vista.civil HAVING age>=18");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }

        return  id;
    }

    @Override
    public  Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(reg_number) FROM grama_vista.civil WHERE TIMESTAMPDIFF(year,dob,now()) >=18");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.civil SET isArchieved=TRUE WHERE reg_number=?", id);
    }

    @Override
    public  boolean updateEmail(Integer id, String to) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.civil SET isEmailSent=TRUE WHERE reg_number=?", id);
    }

    @Override
    public  boolean isMailSent(Object id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT isEmailSent FROM grama_vista.civil WHERE reg_number=?",id);
        if(resultSet.next()){
            return resultSet.getBoolean(1);
        }
        return false;
    }

    @Override
    public  Civil searchbyNIC(String nic) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT *,TIMESTAMPDIFF(year,dob,now()) AS Age FROM grama_vista.civil  WHERE nic=?", nic);
        if (resultSet.next()) {

            return new Civil(resultSet.getString(1), resultSet.getBlob(14),resultSet.getString(3),resultSet.getString(2), resultSet.getString(4),
                    resultSet.getDate(6).toLocalDate(), resultSet.getInt(19),resultSet.getString(5),resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9),resultSet.getString(10), resultSet.getString(11),resultSet.getString(12),resultSet.getDouble(13),resultSet.getString(15));

        }
        return null;
    }

    @Override
    public  Integer getID(String nic) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT reg_number FROM grama_vista.civil WHERE nic=?",nic);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }


}

