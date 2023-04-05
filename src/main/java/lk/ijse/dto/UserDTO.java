package lk.ijse.dto;


import javafx.scene.control.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class UserDTO {
    String employee;
    String division;
    String name;
    String nic;
    String user;
    String password;
    LocalDate dob;
    Integer age;
    LocalDate emDate;
    Double salary;
    Integer contact;
}
