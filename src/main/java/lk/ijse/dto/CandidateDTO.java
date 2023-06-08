package lk.ijse.dto;

import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.sql.Blob;

@Data
@AllArgsConstructor

public class CandidateDTO {
    public String Election;
    public Blob image;
    public String Name;
    public String NIC;
    public String Division;
    public String address;
    public Integer Contact;
    public String Politic;




}