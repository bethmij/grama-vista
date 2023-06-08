package lk.ijse.entity;
import lombok.*;

@Data
@AllArgsConstructor

public class Residence {
    private String home_id;
    private String division_id;
    private String house_holder_name;
    private String address;
    private Integer member_count;
    private Integer count_below_18;
    private String residence_type;
    private String electricity;
    private String water_supply;
}
