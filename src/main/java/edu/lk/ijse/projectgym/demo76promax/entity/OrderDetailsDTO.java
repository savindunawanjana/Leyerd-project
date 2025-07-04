package edu.lk.ijse.projectgym.demo76promax.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private int qty;
    private double total_price;

}