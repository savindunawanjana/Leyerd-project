package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerPayment {

    private String paymentId;
    private String customerId;
    private Date expayerDate;
    private int validNomberOfMonths;
    private String SystemUserRoll;
    private Date paymentDate;
    private BigDecimal paymentAmount;

}
