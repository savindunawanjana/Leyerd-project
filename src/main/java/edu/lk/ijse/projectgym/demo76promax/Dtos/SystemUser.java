package edu.lk.ijse.projectgym.demo76promax.Dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SystemUser {

    private String user_Id;
    private String user_password;
    private String user_Roll; // should be user_role in DB
    private String user_Name;
    private String pon_number; // matches 'phone_number' in DB



}
