package edu.lk.ijse.projectgym.demo76promax.entity;

import org.w3c.dom.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemUser {
    private String userId;
    private String password;
    private String systemrole;
    private String systemUsername;
    private String contactNumber;

}
