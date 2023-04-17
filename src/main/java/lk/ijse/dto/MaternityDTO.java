package lk.ijse.dto;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor

public class MaternityDTO {
    String reg;
    String civil;
    String name;
    Integer month;
    String midWife;
}
