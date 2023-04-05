package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DisableTM {

    String Id;
    String Civil;
    String Name;
    String Disable;
    String Desc;
    Button btn;
}
