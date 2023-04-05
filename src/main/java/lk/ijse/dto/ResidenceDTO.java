package lk.ijse.dto;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResidenceDTO {
    String iD;
    String division;
    String address;
    String holder;
    Integer member;
    Integer below;
    String type;
    String elec;
    String water;
}
