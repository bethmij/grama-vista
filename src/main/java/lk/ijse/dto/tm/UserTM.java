package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserTM {
    String Employee;
    String Division;
    String Name;
    String NIC;
    String User;
    Button btn;
}
