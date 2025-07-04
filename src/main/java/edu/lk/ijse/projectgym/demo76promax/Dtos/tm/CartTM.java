package edu.lk.ijse.projectgym.demo76promax.Dtos.tm;

import javafx.scene.control.Button;
import lombok.*;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public class CartTM {

        private String itemId;
        private String itemName;
        private int cartQty;
        private double unitPrice;
        private double total;
        private Button btnRemove;


    }



