package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.OrderDTO;
import edu.lk.ijse.projectgym.demo76promax.Util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OderModel {

    private final OrderDetailsModel orderDetailsModel = new OrderDetailsModel();

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute(
                "select order_id from orders order by order_id desc limit 1"
        );

        char tableChar = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }


    public boolean placeOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSave = CrudUtil.execute(
                    "insert into orders values (?,?,?)",
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    orderDTO.getDate()
            );

            if (isSave) {
                boolean isDetailsSaved = orderDetailsModel.saveOrderDetailsList(orderDTO.getCartList());
                if (isDetailsSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}










