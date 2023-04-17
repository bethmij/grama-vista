package lk.ijse.dto.tm;

import javafx.scene.control.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SamurdhiTM {
    private String home_id;
    private String civil_id;
    private String houseHolder;
    private String NIC;
    private String address;
    private Integer member;
}
