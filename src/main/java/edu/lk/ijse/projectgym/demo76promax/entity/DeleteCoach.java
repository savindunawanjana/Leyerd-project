package edu.lk.ijse.projectgym.demo76promax.entity;


import org.w3c.dom.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteCoach {
    private int deleteId;
    private String coachId;
    private String Coachname;
    private String deleteDate;
    private String SystemUserId;
    private Text ResonTodelete;
}
