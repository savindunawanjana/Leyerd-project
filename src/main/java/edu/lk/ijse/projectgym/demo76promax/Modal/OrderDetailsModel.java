package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dtos.OrderDetailsDTO;
import edu.lk.ijse.projectgym.demo76promax.dao.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {
    private final ItemModel itemModel = new ItemModel();

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> cartList) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetailsDTO : cartList) {
            boolean isDetailsSaved = saveOrderDetails(orderDetailsDTO);
            if (!isDetailsSaved) {
                return false;
            }

            boolean isUpdated = itemModel.reduceQty(orderDetailsDTO);
            if (!isUpdated) {
                return false;

            }

        }

        return true;

    }


    private boolean saveOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "insert into order_details values (?,?,?,?)",
                dto.getOrderId(),
                dto.getItemId(),
                dto.getQty(),
                dto.getTotal_price()
        );
    }
}