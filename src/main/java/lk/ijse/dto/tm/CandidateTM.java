package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.sql.Blob;

@Data
@AllArgsConstructor

public class CandidateTM {

    public String Election;
    public String Name;
    public String NIC;
    public String Division;
    private Button btn;


}
