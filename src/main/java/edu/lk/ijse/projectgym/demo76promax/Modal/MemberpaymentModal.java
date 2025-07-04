package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.memberPaymentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MemberpaymentModal {

    public String savemethadeFromModel(memberPaymentDto paymentdto) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = " INSERT INTO customer_payment(customer_id,expire_date,valid_number_of_month,system_user_roll,payment_date,payment_amount)VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, paymentdto.getCustomer_id());
        preparedStatement.setString(2, paymentdto.getExpire_date());
        preparedStatement.setInt(3, paymentdto.getValid_number_of_month());
        preparedStatement.setString(4, paymentdto.getSystem_user_role());
        preparedStatement.setString(5, paymentdto.getPayment_date());
        preparedStatement.setDouble(6, paymentdto.getPayment_amount());

        boolean result = preparedStatement.execute();
        System.out.printf(result+"");
        return result != true ? "Saved successfully" : "Not saved";

    }

    public String DubliceteDeliteButten() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = """
            DELETE a FROM customer_payment a
            JOIN customer_payment b ON 
                a.customer_id = b.customer_id AND
                a.expire_date = b.expire_date AND
                a.valid_months = b.valid_months AND
                a.system_user_id = b.system_user_id AND
                a.payment_date = b.payment_date AND
                a.payments = b.payments AND
                a.payment_id > b.payment_id
        """;

        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(sql);
        return count > 0 ? "Duplicate entries deleted successfully" : "No duplicates to delete";
    }

    public ObservableList<memberPaymentDto> getAllmethod() throws SQLException, ClassNotFoundException {
        ObservableList<memberPaymentDto> members = FXCollections.observableArrayList();

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM customer_payment";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {


            members.add(new memberPaymentDto(
                    resultSet.getString("payment_id"),
                    resultSet.getString("customer_id"),
                    resultSet.getString("expire_date"),
                    resultSet.getInt("valid_number_of_month"),
                    resultSet.getString("system_user_roll"),
                    resultSet.getString("payment_date"),
                    resultSet.getDouble("payment_amount")));

        }

        return members;
    }

    public String deleteLowerPaymentIdsPerCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = """
            DELETE FROM customer_payment
            WHERE payment_id NOT IN (
                SELECT * FROM (
                    SELECT MAX(payment_id)
                    FROM customer_payment
                    GROUP BY customer_id
                ) AS latest
            )
        """;

        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(sql);
        return deletedRows > 0 ? "Deleted older payments successfully" : "No old payments to delete.";
    }

    // Removed serchMethodfrompassword because the column `password` does not exist in customer_payment
}
