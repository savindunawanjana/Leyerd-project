package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dtos.ItemDTO;
import  edu.lk.ijse.projectgym.demo76promax.Dtos.OrderDetailsDTO;
import edu.lk.ijse.projectgym.demo76promax.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ItemModel {
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute(
                "select item_id from items"
        );
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }

    public ItemDTO findById(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute(
                "select * from items where item_id=?",
                itemId
        );

        if (rst.next()) {
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update items set qty = qty-? where item_id=?",
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getItemId()
        );
    }

}