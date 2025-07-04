package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.comanPasswordobject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class comanPasswordmodal {

    // Update the common password securely with a prepared statement
    public static String setComanPassword(String password) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE coman_password SET password = ? WHERE id = 1";

        try (Connection con = Dbconnection.getObject().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, password);
            int result = pst.executeUpdate();

            return result > 0 ? "Update successful" : "Update not successful";
        }
    }

    // Retrieve the common password (assumes only one row with id=1)
    public static String getComanPassword(String textfild) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM coman_password WHERE id = 1";

        try (Connection con = Dbconnection.getObject().getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            if (rst.next()) {
                return rst.getString("password");
            }
            return null;  // or "" depending on what you prefer
        }
    }

    // Get all passwords as ObservableList (not typical for passwords, but included if needed)
    public static ObservableList<comanPasswordobject> getComanPasswordFromDatabases() throws SQLException, ClassNotFoundException {
        ObservableList<comanPasswordobject> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM coman_password";

        try (Connection con = Dbconnection.getObject().getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                comanPasswordobject obj = new comanPasswordobject();
                obj.setComanPassword(rst.getString("password"));
                list.add(obj);
            }
        }

        return list;
    }
}
