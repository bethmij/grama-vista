package lk.ijse.dto.tm;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DivisionTM {
    private String ID;
    private String Name;
    private String Secretary;
    private String Admin;
    private Integer Population;
    private Double Land;
    private Button Delete;
}
