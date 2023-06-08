package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LandDetailEntity {
    private Integer type_id;
    private Integer land_num;
    private String land_type;
}
