package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class itemModel2 {

    public boolean saveItem(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO items (item_id, name,qty, unit_price, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection con = Dbconnection.getObject().getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, item.getItemId());
            pst.setString(2, item.getName());
            pst.setInt(3, item.getQuantity());
            pst.setDouble(4, item.getUnitPrice());
            pst.setString(5, item.getSupplier_id());
            return pst.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE items SET name = ?,qty = ?, unit_price = ?, supplier_id = ? WHERE item_id = ?";
        try (Connection con = Dbconnection.getObject().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, item.getName());
            pst.setInt(2, item.getQuantity());
            pst.setDouble(3, item.getUnitPrice());
            pst.setString(4, item.getSupplier_id());
            pst.setString(5, item.getItemId());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean deleteItem(String itemId) throws SQLException {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (Connection con = Dbconnection.getObject().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, itemId);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<ItemDTO> getAllItems() throws SQLException {
        String sql = "SELECT * FROM items";
        List<ItemDTO> itemList = new ArrayList<>();
        try (Connection con = Dbconnection.getObject().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                itemList.add(new ItemDTO(
                        rs.getString("item_id"),
                        rs.getString("name"),
                        rs.getInt("qty"),
                        rs.getDouble("unit_price"),
                        rs.getString("supplier_id")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
    public List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        List<String> ids = new ArrayList<>();
        String sql = "SELECT supplier_id FROM supplier";
        Connection con = Dbconnection.getObject().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ids.add(rs.getString("supplier_id"));
        }
        return ids;
    }



}
