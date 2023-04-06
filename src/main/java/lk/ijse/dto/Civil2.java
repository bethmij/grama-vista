package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Civil2 {
    private String id;
    private String name;
    private Blob image;
    private String nic;
    private Integer age;
    private String address;
    private LocalDate dob;
    private String gender;
    private String marriage;
    private String relation;
    private String edu_status;
    private String school;
    private String occupation;
    private String working_address;
    private Double salary;
    private Integer contact1;
    private Integer contact2;

}
