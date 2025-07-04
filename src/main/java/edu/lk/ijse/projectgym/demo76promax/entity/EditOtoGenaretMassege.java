package edu.lk.ijse.projectgym.demo76promax.entity;

import org.w3c.dom.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class EditOtoGenaretMassege {

    private int massegeId;//aoutoIncrimant
    private  String SystemUsearId;
    private Date messageDate;

}
