package edu.lk.ijse.projectgym.demo76promax.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OderDeatiles {

    private String orderId;
    private String ItemId;
    private int quantity;
    private BigDecimal price;

}
