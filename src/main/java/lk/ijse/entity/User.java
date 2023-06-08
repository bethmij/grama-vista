package lk.ijse.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor

public class User {
    String employee;
    String division;
    String name;
    String nic;
    String user;
    String password;
    LocalDate dob;
    Integer age;
    LocalDate emDate;
    Integer contact;
    String email;




}
