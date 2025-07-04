package edu.lk.ijse.projectgym.demo76promax.Dtos.tm;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {

    private  String id ;
    private String name ;
    private String address;
    private String CuctermerNumber;
    private String CuctermerBirthdayMonth ;
    private int CuctermerWeight ;
    private String CuctermerEmail ;
    private Double CuctermerRegisterFees ;

}