package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LandTM {
    String ID;
    String Plan;
    Double Area;
    String Gov;
    String Cultivate;
    Button btn;
}
