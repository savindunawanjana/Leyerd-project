package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker {
    private String workerId;
    private String name;
    private String contactNumber;
    private String systemUserRole;
    private Date addDate;
    private String email;
}
