package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResidenceTM {
    String Id;
    String Division;
    String Address;
    String Holder;
    Button btn;
    Button btn1;
}
