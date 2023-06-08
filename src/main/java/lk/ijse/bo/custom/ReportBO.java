package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;

public interface ReportBO {
    void saveDetail(DetailDTO detail) throws SQLException;
}
