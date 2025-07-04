package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeTable {
    private String employeeId;
    private String employeeName;
    private String employeeContact;
    private String employeeRole;
    private String employeeEmail;

}
