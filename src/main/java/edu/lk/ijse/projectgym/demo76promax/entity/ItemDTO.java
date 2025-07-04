package edu.lk.ijse.projectgym.demo76promax.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String itemId;
    private String name;
    private int quantity;
    private double unitPrice;
    private String supplier_id;


}