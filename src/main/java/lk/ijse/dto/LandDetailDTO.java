package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LandDetailDTO {
    private Integer type_id;
    private Integer land_num;
    private String land_type;
}
