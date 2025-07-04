package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Coach {
    private String coachId;
    private String name;
    private String contactNumber;
    private String systemUserRole;
    private Date addDate;
    private String email;

}
