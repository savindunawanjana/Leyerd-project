package edu.lk.ijse.projectgym.demo76promax.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDataDto {

    private String id;
    private String name;
    private String number;
    private String role ;
    private String email;


    public EmployeeDataDto (String employeeId, String rolle, String name) {

        this.id = employeeId;
        this.role = rolle;
        this.name= name;
    }

//
//    public class EmployeeAttendanceDto {
//        private String employeeId;
//        private String name;
//        private String role;
//        private String attendanceDate;
//        private String status; // e.g., "Present", "Absent", "Late", "Half Day"
//    }





}
