package lk.ijse.dto.tm;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DivisionTM {
    public String ID;
    public String Name;
    public String Secretary;
    public String Admin;
    public Integer Population;
    public Double Land;
    private Button Delete;
}
