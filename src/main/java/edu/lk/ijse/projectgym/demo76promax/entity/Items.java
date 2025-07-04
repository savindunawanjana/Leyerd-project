package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Items {
    private String itemId;
    private String itemName;
    private int itemqty;
    private BigDecimal unitPrice;
    private String supplierId;
}
