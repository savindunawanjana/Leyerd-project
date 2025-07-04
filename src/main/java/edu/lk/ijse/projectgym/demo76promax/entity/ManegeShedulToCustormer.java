package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManegeShedulToCustormer {
    private int scheduleId;
    private String SystemUsrId;
    private String customerId;
    private Date sendDate;
}
