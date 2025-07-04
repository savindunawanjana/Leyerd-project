package edu.lk.ijse.projectgym.demo76promax.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdditionalLeaveWorker {
    private int leaveId;
    private String workerId;
    private String systemUserId;
    private Date leaveDate;
}
