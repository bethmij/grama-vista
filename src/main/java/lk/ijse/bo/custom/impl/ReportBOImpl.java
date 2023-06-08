package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RegistrationBO;
import lk.ijse.bo.custom.ReportBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;

public class ReportBOImpl implements RegistrationBO, ReportBO {
    DetailDAO detailDAO = new DetailDAOImpl();

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
