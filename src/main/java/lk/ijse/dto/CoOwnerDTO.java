package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CoOwnerDTO {
    private Integer land_id;
    private String civil_id;
    private String lot_num;
    private Double percentage;
}
