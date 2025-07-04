package edu.lk.ijse.projectgym.demo76promax.Dtos;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 5/10/2025 4:16 PM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    private String orderId;
    private String customerId;
    private Date date;
    private ArrayList<OrderDetailsDTO> cartList;

}