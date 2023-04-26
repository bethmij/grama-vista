package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor

public class Candidate2TM {

    private String Election;
    private String address;
    private Integer Contact;
    private String Politic;
    private Button btn;


}
