package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class DeadTM {
    private String Id;
    private String Civil;
    private String Name;
    private LocalDate Date;
    private Integer Age;
    private Button btn;
}
