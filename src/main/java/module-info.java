module edu.lk.ijse.projectgym.demo76promax {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;
    requires jdk.compiler;
    requires java.desktop;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens edu.lk.ijse.projectgym.demo76promax.Dtos to javafx.base;//table eke data pennuwe na meka danakam
    opens edu.lk.ijse.projectgym.demo76promax.Controller to javafx.fxml;
    exports edu.lk.ijse.projectgym.demo76promax;
    opens edu.lk.ijse.projectgym.demo76promax.Dtos.tm to javafx.base;

}