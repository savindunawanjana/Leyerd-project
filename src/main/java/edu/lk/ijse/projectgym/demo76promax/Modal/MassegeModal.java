package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;

import javax.swing.*;
import java.sql.*;

public class MassegeModal {

    public void saveMessage(String text) throws SQLException, ClassNotFoundException {
        Connection con = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO lable (massege) VALUES (?)"; // replace `message_text` with actual column name
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, text);

        int result = pst.executeUpdate(); // returns number of rows affected

        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Message saved successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save message.");
        }
    }



    public String getmassege() throws SQLException, ClassNotFoundException {

        Connection con = Dbconnection.getObject().getConnection();
        String sql = "select * from lable";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);




        if(rs.next()){

            return rs.getString("massege");

        }
        else{


            return "note ofer for yor you";

        }

    }


    public void deletemethod(String massege) throws SQLException, ClassNotFoundException {


        Connection con = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM lable WHERE massege = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, massege);
        int result = pst.executeUpdate();
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Message deleted successfully.");

        }

    }


}
