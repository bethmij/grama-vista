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

    private String Election;
    private String Name;
    private String NIC;
    private String Division;
    private Button btn;


}
