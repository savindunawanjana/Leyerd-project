package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.ItemDTO;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SuplayerDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.ExsaisTm;
import edu.lk.ijse.projectgym.demo76promax.Modal.SuplayerModel;

import edu.lk.ijse.projectgym.demo76promax.Modal.itemModel2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddItemsController implements Initializable {


    private final String idPattern = "^I.*$";

    public Button btnresetId;
    private SuplayerModel suplayerModel = new SuplayerModel();
    private SuplayerDto suplayerDto = new SuplayerDto();
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView tblItems;

    public TextField txtItemId;
    public TextField txtName;
    public TextField txtQty;
    public TextField txtUnitPrice;
    public ComboBox cmbId;

    @FXML
    private TableColumn<SuplayerDto, String> colItemId;

    @FXML
    private TableColumn<SuplayerDto, String> colName;

    @FXML
    private TableColumn<ExsaisTm, Integer> colQty;

    @FXML
    private TableColumn<ExsaisTm, Double> colUnitPrice;

    @FXML
    private TableColumn<ExsaisTm, String> colSupplierId;


    private itemModel2 itemModel2 = new itemModel2();

    private SuplayerModel model = new SuplayerModel();

    public void lodetable() throws SQLException, ClassNotFoundException {

        ObservableList<ItemDTO> dtos = FXCollections.observableArrayList(itemModel2.getAllItems());

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));

        tblItems.setItems(dtos);
    }


    public void buttenAdd(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        boolean isvalidid = id.matches(idPattern);
        txtItemId.setStyle("-fx-border-color: green;");
        if (!isvalidid) {
            txtItemId.setStyle("-fx-border-color: red;");
        }


        if (isvalidid) {
            try {

                String itemId = txtItemId.getText();
                String name = txtName.getText();
                int quantity = Integer.parseInt(txtQty.getText());
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                String supplierId = (String) cmbId.getValue();

                ItemDTO newItem = new ItemDTO(itemId, name, quantity, unitPrice, supplierId);

                boolean isSaved = itemModel2.saveItem(newItem);



                if (isSaved) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!");
                    alert.show();

                    lodetable();
                    clearFields();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save item!");
                    alert.show();
                }
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            }

        }


    }

    public void buttenUpdate(ActionEvent actionEvent) {
        try {
            String itemId = txtItemId.getText();
            String name = txtName.getText();
            int quantity = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            String supplierId = (String) cmbId.getValue();

            ItemDTO updatedItem = new ItemDTO(itemId, name, quantity, unitPrice, supplierId);

            boolean isUpdated = itemModel2.updateItem(updatedItem);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
                lodetable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update item!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }


    public void btnDelete(ActionEvent actionEvent) {
        try {
            String itemId = txtItemId.getText();

            boolean isDeleted = itemModel2.deleteItem(itemId);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!").show();
                lodetable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete item!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }


    private void clearFields() {
        txtItemId.clear();
        txtName.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        cmbId.getSelectionModel().clearSelection();

        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void reset_on_action(ActionEvent actionEvent) {
        clearFields();
    }


    public void On_mouseClicked_for_table(MouseEvent mouseEvent) {
        ItemDTO selected = (ItemDTO) tblItems.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtItemId.setText(selected.getItemId());
            txtName.setText(selected.getName());
            txtQty.setText(String.valueOf(selected.getQuantity()));
            txtUnitPrice.setText(String.valueOf(selected.getUnitPrice()));
            cmbId.setValue(selected.getSupplier_id());

            btnAdd.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ArrayList<SuplayerDto> list = suplayerModel.getAll();
            ArrayList<String> suplayerIds = new ArrayList<>();
            for (SuplayerDto dto : list) {
                suplayerIds.add(dto.getSupplier_id());
            }
            cmbId.setItems(FXCollections.observableArrayList(suplayerIds));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lodetable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }


}
