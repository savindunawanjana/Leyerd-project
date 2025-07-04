package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeAttendance {
    private int attendanceId;
    private String employeeId;
    private String jobRole;
    private Date attendanceDate;
    private String status;
    private String description;

}
