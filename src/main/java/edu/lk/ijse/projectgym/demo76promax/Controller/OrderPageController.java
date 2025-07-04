package edu.lk.ijse.projectgym.demo76promax.Controller;


import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.ItemDTO;
import edu.lk.ijse.projectgym.demo76promax.Dtos.OrderDTO;
import edu.lk.ijse.projectgym.demo76promax.Dtos.OrderDetailsDTO;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.CartTM;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.CustomerTM;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.ItemModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.OderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    private Double totleAmount = 0.0;
    public AnchorPane anchorPane;
    public Label lblItemNetTotles;
    public TextField txtpaidSalary;
    public Label lblBalans;
    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<CartTM, String> colItemId;

    @FXML
    private TableColumn<CartTM, String> colName;

    @FXML
    private TableColumn<CartTM, Double> colPrice;

    @FXML
    private TableColumn<CartTM, Integer> colQuantity;

    @FXML
    private TableColumn<CartTM, Double> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    private Label lblOrderId;

    //  @FXML
    // private Label orderDate;
private CustormerModel custormerModel = new  CustormerModel();

    @FXML
    private TextField txtAddToCartQty;

    private final OderModel orderModel = new OderModel();
    private final CustormerModel customerModel = new CustormerModel();
    private final ItemModel itemModel = new ItemModel();

    private final ObservableList<CartTM> cartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblItemNetTotles.setText(String.valueOf(totleAmount));

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        tblCart.setItems(cartData);

        try {
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(
                    Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK
            ).show();
        }
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(orderModel.getNextOrderId());

//        orderDate.setText(String.valueOf(LocalDate.now()));
        //   orderDate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadItemIds();
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
//        cmbItemId
        cmbItemId.setItems(
                FXCollections.observableArrayList(
                        itemModel.getAllItemIds()
                )
        );
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIdsList = customerModel.getAllCustomerIds();
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        customerIds.addAll(customerIdsList);
        cmbCustomerId.setItems(customerIds);

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
//      String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        String selectedItemId = cmbItemId.getValue();
        String cartQtyString = txtAddToCartQty.getText();

        if (selectedItemId == null) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please select item..!"
            ).show();
            return;
        }

        if (!cartQtyString.matches("^[0-9]+$")) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please enter valid quantity..!"
            ).show();
            return;
        }

        int cartQty = Integer.parseInt(cartQtyString);
        int itemStockQty = Integer.parseInt(lblItemQty.getText());

        // 10 < 15
        if (itemStockQty < cartQty) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Not enough item quantity..!"
            ).show();
            return;
        }

        String itemName = lblItemName.getText();
        double itemUnitPrice = Double.parseDouble(lblItemPrice.getText());
        double total = itemUnitPrice * cartQty;

        totleAmount = totleAmount + total;
        lblItemNetTotles.setText(String.valueOf(totleAmount));

        // / ///////////////////////////////////////

        for (CartTM cartTM : cartData) {
            if (cartTM.getItemId().equals(selectedItemId)) {
                // 20 + 10
                int newQty = cartTM.getCartQty() + cartQty;

                if (itemStockQty < newQty) {
                    new Alert(
                            Alert.AlertType.WARNING,
                            "Not enough item quantity..!"
                    ).show();
                    return;
                }

                cartTM.setCartQty(newQty);
                cartTM.setTotal(newQty * itemUnitPrice);

                txtAddToCartQty.setText("");
                tblCart.refresh();

                return;
            }
        }

        Button removeBtn = new Button("Remove");
        CartTM cartTM = new CartTM(
                selectedItemId,
                itemName,
                cartQty,
                itemUnitPrice,
                total,
                removeBtn
        );

        removeBtn.setOnAction(action -> {
            cartData.remove(cartTM);
            tblCart.refresh();
        });

        txtAddToCartQty.setText("");
        cartData.add(cartTM);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (tblCart.getItems().isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please add items to cart..!"
            ).show();
            return;
        }

        if (cmbCustomerId.getValue().isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please select customer for place order..!"
            ).show();
            return;
        }

        String selectedCustomerId = cmbCustomerId.getValue();
        String orderId = lblOrderId.getText();
        Date date = Date.valueOf(LocalDate.now());

        ArrayList<OrderDetailsDTO> cartList = new ArrayList<>();

        for (CartTM cartTM : cartData) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderId,
                    cartTM.getItemId(),
                    cartTM.getCartQty(),
                    cartTM.getUnitPrice()
            );
            cartList.add(orderDetailsDTO);
        }

        OrderDTO orderDTO = new OrderDTO(
                orderId,
                selectedCustomerId,
                date,
                cartList
        );

        try {
            boolean isPlaced = orderModel.placeOrder(orderDTO);

            if (isPlaced) {
                resetPage();
                //----------------------------------------------------------->
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " payment Sucsessfully ");
                alert.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to place order..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to place order..!").show();
        }

        lblItemNetTotles.setText(String.valueOf(totleAmount));


        if (txtpaidSalary.getText() == null || txtpaidSalary.getText().equals("")) {

            lblBalans.setText("0.0");

        } else {
            try {
                double paidSalary = Double.parseDouble(txtpaidSalary.getText());

                if (paidSalary > totleAmount) {

                    double balance = paidSalary - totleAmount;
                    System.out.println("Extra paid: " + balance);
                    lblBalans.setText(balance + "");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, " payment Sucsessfully ");
                    alert.show();
                    return;

                } else if (paidSalary < totleAmount) {


                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "please pay Avelable payment");
                    alert.show();
                    return;

                }


            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input: " + txtpaidSalary.getText());

            }
        }


    }


    @FXML
    void btnResetOnAction(ActionEvent event) {

        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblCustomerName.setText("");
        lblItemName.setText("");
        lblItemQty.setText("");
        lblItemPrice.setText("");
        txtAddToCartQty.clear();
        txtpaidSalary.clear();
        lblBalans.setText("0.0");


        cartData.clear();
        tblCart.refresh();


        totleAmount = 0.0;
        lblItemNetTotles.setText("0.0");

        try {

            lblOrderId.setText(orderModel.getNextOrderId());

            // Reload combo boxes
            loadCustomerIds();
            loadItemIds();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error while resetting form!").show();
        }
    }


    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedId = cmbCustomerId.getSelectionModel().getSelectedItem();
        String name = customerModel.findNameById(selectedId);
        lblCustomerName.setText(name);
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedId = cmbItemId.getSelectionModel().getSelectedItem();
        ItemDTO itemDTO = itemModel.findById(selectedId);

        if (itemDTO != null) {
            lblItemName.setText(itemDTO.getName());
            lblItemQty.setText(String.valueOf(itemDTO.getQuantity()));
            lblItemPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        } else {
            lblItemName.setText("");
            lblItemQty.setText("");
            lblItemPrice.setText("");
        }
    }


    public void On_action_riport(ActionEvent actionEvent) {
        try {

            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/oderDtls.jrxml")
            );


            Connection connection = Dbconnection.getObject().getConnection();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("p_date",LocalDate.now().format(formatter));


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);


            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Order Details Report");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



