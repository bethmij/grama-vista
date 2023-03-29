package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Civil {
    private String name;
    private String nic;
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


}
