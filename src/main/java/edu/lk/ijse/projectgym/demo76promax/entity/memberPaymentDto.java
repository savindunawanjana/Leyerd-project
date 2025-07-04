package edu.lk.ijse.projectgym.demo76promax.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class memberPaymentDto {

    private String payment_id;
    private String customer_id;
    private String expire_date;
    private int valid_number_of_month;
    private String system_user_role;
    private String payment_date;
    private double payment_amount;



    public memberPaymentDto(String customerId, String expayeDate, int monthCount, String userRoll, String dateNow, double payment) {

        this.customer_id = customerId;
        this.expire_date = expayeDate;
        this.valid_number_of_month = monthCount;
        this.system_user_role = userRoll;
        this.payment_date = dateNow;
        this.payment_amount = payment;

    }
}
