package edu.lk.ijse.projectgym.demo76promax.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String customerNumber;
    private String customerBirthday;
    private int customerWeight;
    private String customerEmail;
    private Double customerRegisterFees;
}
