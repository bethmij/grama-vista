package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor

public class User {

    private String  division_id;
    private String employee_num;
    private String nic;
    private String name;
    private String user;
    private String password;
    private LocalDate date;
    private LocalDate employee_date;
    private String email;
    private Integer contact;



}
