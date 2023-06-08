package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Civil {
    String iD;
    Blob image;
    String name;
    String nic;
    String address;
    LocalDate dob;
    Integer age;
    String gender;
    String marriage;
    String relation;
    String education;
    String school;
    String occupation;
    String work;
    Double salary;
    String email;


}
