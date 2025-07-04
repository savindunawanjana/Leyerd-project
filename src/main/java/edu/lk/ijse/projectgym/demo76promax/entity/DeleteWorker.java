package edu.lk.ijse.projectgym.demo76promax.entity;

import org.w3c.dom.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DeleteWorker {
    private int deleteId;
    private String workerId;
    private String workername;
    private String deleteDate;
    private String SystemUserId;
    private Text ResonTodelete;
}
