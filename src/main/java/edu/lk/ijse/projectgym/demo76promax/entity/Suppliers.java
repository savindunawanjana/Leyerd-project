package edu.lk.ijse.projectgym.demo76promax.entity;


import org.w3c.dom.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Suppliers {

    private String supplierId;
    private String name;
    private String contactNumber;
    private String email;
    private Text address;
}
