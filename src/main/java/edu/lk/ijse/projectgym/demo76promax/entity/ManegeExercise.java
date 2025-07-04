package edu.lk.ijse.projectgym.demo76promax.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManegeExercise {
    private int exerciseId;
    private String exerciseName;
    private String category;
    private Date addDate;
    private String SystemUserId;
}
