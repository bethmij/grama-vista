package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DetailFormBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailFormBOImpl implements DetailFormBO {
    DetailDAO detailDAO = new DetailDAOImpl();

    public List<DetailDTO> getDetail(Integer number) throws SQLException {
        List<Detail> details = detailDAO.search(number);
        List<DetailDTO> detailDTOS = new ArrayList<>();
        for (Detail detail : details) {
            detailDTOS.add(new DetailDTO(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription()));
        }
        return detailDTOS;
    }

    public List<DetailDTO> searchAllDetail() throws SQLException {
        List<Detail> details = detailDAO.searchAll();
        List<DetailDTO> detailDTOS = new ArrayList<>();
        for (Detail detail : details) {
            detailDTOS.add(new DetailDTO(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription()));
        }
        return detailDTOS;
    }
}
