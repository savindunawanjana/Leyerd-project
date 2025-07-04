package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeePayments {
    private String paymentId;
    private String systemUserRoll;
    private String employeeRole;
    private String employeeId;
    private Date paymentDate;
    private BigDecimal salary;
}
