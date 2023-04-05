package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class MaternityTM {
    String Reg;
    String Civil;
    String Name;
    LocalDate Date;
    Integer Month;
    String MidWife;
    Button btn;
}
