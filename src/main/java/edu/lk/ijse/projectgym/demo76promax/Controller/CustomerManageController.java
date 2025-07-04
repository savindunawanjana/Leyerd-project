package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerManageController implements Initializable {

    public AnchorPane ancpaneId;
    public HBox lblcustid;
    public TextField txtCname;
    public TextField txtCaddres;
    public TextField txtNumber;
    public TextField txtBirdMonth;
    public TextField txtWhight;
    public TextField txtemailAdress;
    public TextField txtRfess;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public TableView tableId;
    public Label lblCustomerId;

    // Validation Patterns
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^\\d{10}$"; // Only allows 10 digit numbers

    @FXML
    private TableColumn<CustermerDto, String> CustormerId2;
    @FXML
    private TableColumn<CustermerDto, String> custermeraddress;
    @FXML
    private TableColumn<CustermerDto, String> Custormername;
    @FXML
    private TableColumn<CustermerDto, String> contactnumber;
    @FXML
    private TableColumn<CustermerDto, String> custermerbirthdayMonth;
    @FXML
    private TableColumn<CustermerDto, Integer> cutermerwhight;
    @FXML
    private TableColumn<CustermerDto, String> custermeremailaddress;
    @FXML
    private TableColumn<CustermerDto, Double> registerfees;

    private CustormerModel custormerModel = new CustormerModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            Lodetable();
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void Lodetable() throws SQLException, ClassNotFoundException {
        ObservableList<CustermerDto> cach = FXCollections.observableArrayList(custormerModel.getAllCustomers());
        CustormerId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        Custormername.setCellValueFactory(new PropertyValueFactory<>("name"));
        custermeraddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactnumber.setCellValueFactory(new PropertyValueFactory<>("cuctermerNumber"));
        custermerbirthdayMonth.setCellValueFactory(new PropertyValueFactory<>("cuctermerBirthdayMonth"));
        cutermerwhight.setCellValueFactory(new PropertyValueFactory<>("cuctermerWeight"));
        custermeremailaddress.setCellValueFactory(new PropertyValueFactory<>("cuctermerEmail"));
        registerfees.setCellValueFactory(new PropertyValueFactory<>("cuctermerRegisterFees"));
        tableId.setItems(cach);
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = custormerModel.getNextId();
        lblCustomerId.setText(nextId);
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        Lodetable();
        btnDelete.setVisible(true);
        btnUpdate.setVisible(true);
        btnSave.setVisible(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);

        txtCname.clear();
        txtNumber.clear();
        txtCaddres.clear();
        txtBirdMonth.clear();
        txtWhight.clear();
        txtemailAdress.clear();
        txtRfess.clear();
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        resetPage();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = lblCustomerId.getText();
        if (!custormerModel.isUserIdExists(id)) {
            new Alert(Alert.AlertType.ERROR, "ID is not available").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean isDelete = custormerModel.deleteCustomerById(id);
                    if (isDelete) {
                        new Alert(Alert.AlertType.INFORMATION, "Delete successful").show();
                        resetPage();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error occurred while deleting: " + e.getMessage()).show();
                }
            }
        });
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        CustermerDto customer = new CustermerDto(
                lblCustomerId.getText().trim(),
                txtCname.getText().trim(),
                txtCaddres.getText().trim(),
                txtNumber.getText().trim(),
                txtBirdMonth.getText().trim(),
                Integer.parseInt(txtWhight.getText().trim()),
                txtemailAdress.getText().trim(),
                Double.parseDouble(txtRfess.getText().trim())
        );

        try {
            String cach = custormerModel.updateCustomer(customer);
            new Alert(Alert.AlertType.INFORMATION, cach).show();
            resetPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Update Error: " + e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String email = txtemailAdress.getText();
        String phone = txtNumber.getText();

        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);


        txtemailAdress.setStyle("-fx-border-color: #BB25B9;");
        txtNumber.setStyle("-fx-border-color: #BB25B9;");

        if (!isValidPhone) {
            txtNumber.setStyle("-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtemailAdress.setStyle("-fx-border-color: red;");
        }

        if (isValidPhone && isValidEmail) {
            CustermerDto customer = new CustermerDto(
                    lblCustomerId.getText(),
                    txtCname.getText(),
                    txtCaddres.getText(),
                    txtNumber.getText(),
                    txtBirdMonth.getText(),
                    Integer.parseInt(txtWhight.getText()),
                    txtemailAdress.getText(),
                    Double.parseDouble(txtRfess.getText())
            );

            try {
                String cach = custormerModel.saveCustomer(customer);
                new Alert(Alert.AlertType.INFORMATION, cach).show();
                resetPage();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Save Error: " + e.getMessage()).show();
            }
        }
    }

    public void txtNameChange(KeyEvent keyEvent) {

    }

    public void on_mousClicked(MouseEvent mouseEvent) {
        CustermerDto custermerDto = (CustermerDto) tableId.getSelectionModel().getSelectedItem();

        if (custermerDto != null) {
            lblCustomerId.setText(custermerDto.getId());
            txtCname.setText(custermerDto.getName());
            txtCaddres.setText(custermerDto.getAddress());
            txtNumber.setText(custermerDto.getCuctermerNumber());
            txtBirdMonth.setText(custermerDto.getCuctermerBirthdayMonth());
            txtWhight.setText(String.valueOf(custermerDto.getCuctermerWeight()));
            txtemailAdress.setText(custermerDto.getCuctermerEmail());
            txtRfess.setText(String.valueOf(custermerDto.getCuctermerRegisterFees()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void on_mous_clicked(MouseEvent mouseEvent) throws IOException {
        ancpaneId.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CustormerDorrPayment.fxml"));
        ancpaneId.getChildren().add(parent);
    }
}
