package edu.lk.ijse.projectgym.demo76promax.entity;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SystemUserDalete {

    private int deleteId;
     private String systemUserId ;
     private Date deleteDate ;
     private String ResonTodelete ;
}
