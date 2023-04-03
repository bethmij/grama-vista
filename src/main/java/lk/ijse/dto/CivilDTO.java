package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class CivilDTO {
    String iD;
    Blob image;
    String name;
    String nic;
    String address;
    String dob;
    Integer age;
    String gender;
    String marriage;
    String relation;
    String education;
    String school;
    String occupation;
    String work;
    Double salary;
}
