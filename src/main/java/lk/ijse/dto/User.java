package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User {

    private String  division_id;
    private String employee_num;
    private String nic;
    private String name;
    private String user;
    private String password;
    private LocalDate date;
    private LocalDate employee_date;
    private Double salary;
    private Integer contact;



}
