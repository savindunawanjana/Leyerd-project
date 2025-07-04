package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustormerModel {

    // Save a new customer
    public String saveCustomer(CustermerDto customer) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getCuctermerNumber());
        preparedStatement.setString(5, customer.getCuctermerBirthdayMonth());
        preparedStatement.setInt(6, customer.getCuctermerWeight());
        preparedStatement.setString(7, customer.getCuctermerEmail());
        preparedStatement.setDouble(8, customer.getCuctermerRegisterFees());

        int result = preparedStatement.executeUpdate();
        return result > 0 ? "Saved successfully" : "Not saved";
    }

    // Delete a customer by ID (using parameterized query)
    public boolean deleteCustomerById(String customerId) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customerId);

        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    // Get all customers
    public ArrayList<CustermerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustermerDto> customerList = new ArrayList<>();
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM customer";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            customerList.add(new CustermerDto(
                    result.getString("customer_id"),
                    result.getString("customer_name"),
                    result.getString("customer_address"),
                    result.getString("contact_number"),
                    result.getString("customer_birthday"),
                    result.getInt("customer_weight"),
                    result.getString("customer_email"),
                    result.getDouble("register_fees")
            ));
        }
        return customerList;
    }

    // Update a customer
    public String updateCustomer(CustermerDto customer) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "UPDATE customer SET customer_name = ?, customer_address = ?, contact_number = ?, " +
                "customer_birthday = ?, customer_weight = ?, customer_email = ?, register_fees = ? " +
                "WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getCuctermerNumber());
        preparedStatement.setString(4, customer.getCuctermerBirthdayMonth());
        preparedStatement.setInt(5, customer.getCuctermerWeight());
        preparedStatement.setString(6, customer.getCuctermerEmail());
        preparedStatement.setDouble(7, customer.getCuctermerRegisterFees());
        preparedStatement.setString(8, customer.getId());

        int result = preparedStatement.executeUpdate();
        return result > 0 ? "Update successful" : "Update unsuccessful";
    }

    // Generate the next customer ID (e.g., C0001, C0002, ...)
    public String getNextId() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

        char prefix = 'C';

        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // e.g., "C004"
            String numberPart = lastId.substring(1); // e.g., "004"
            int lastIdNumber = Integer.parseInt(numberPart);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(prefix + "%03d", nextIdNumber);
        }
        return prefix + "001"; // First ID if none exists
    }

    // Check if a customer ID exists
    public boolean isUserIdExists(String id) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT customer_id FROM customer WHERE customer_id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, id);

        ResultSet resultSet = pst.executeQuery();
        return resultSet.next();
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute(
                "select  customer_id from customer"
        );
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);

        }
        return list;
    }

    public String findNameById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute(
                "select customer_name from customer where customer_id =?",
                customerId
        );
        if (rst.next()) {
            return rst.getString(1);
        }
        return "";
    }

    public String findEmailById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute(
                "SELECT customer_email FROM customer WHERE customer_id = ?",
                customerId
        );
        if (rst.next()) {
            return rst.getString(1);
        }
        return "";
    }



}
