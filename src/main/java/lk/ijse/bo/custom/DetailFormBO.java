package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface DetailFormBO {
    List<DetailDTO> getDetail(Integer number) throws SQLException;

    List<DetailDTO> searchAllDetail() throws SQLException;
}
