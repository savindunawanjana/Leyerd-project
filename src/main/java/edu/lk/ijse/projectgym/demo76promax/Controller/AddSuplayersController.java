package edu.lk.ijse.projectgym.demo76promax.Controller;

import com.sun.source.tree.CaseLabelTree;
import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SuplayerDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.SuplayerModel;
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
import java.util.ResourceBundle;

public class AddSuplayersController implements Initializable {


    private final String idPattern = "^S.*$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^\\d{10}$";
    //"^(\\d+)||((\\d+\\.)(\\d){2})$";



    public TextField txtSupplierId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextArea txtAddress;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView tblSuppliers;

    @FXML
    private TableColumn<SuplayerDto, String> colId;
    @FXML
    private TableColumn<SuplayerDto, String> colName;
    @FXML
    private TableColumn<SuplayerDto, String> colPhone;
    @FXML
    private TableColumn<SuplayerDto, String> colEmail;
    @FXML
    private TableColumn<SuplayerDto, String> colAddress;
    private SuplayerModel suplayerModel = new SuplayerModel();

    public void saveMethod() {

        String id = txtSupplierId.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        boolean isvalidid = id.matches(idPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);


        txtSupplierId.setStyle("-fx-border-color: green;");
        txtEmail.setStyle("-fx-border-color: green;");
        txtPhone.setStyle("-fx-border-color: green;");

        if (!isvalidid) {
            txtSupplierId.setStyle("-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtEmail.setStyle("-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtPhone.setStyle("-fx-border-color: red;");
        }


        if (isvalidid && isValidEmail && isValidPhone){

            String id1 = txtSupplierId.getText();
        String Name = txtName.getText();
        String Phone = txtPhone.getText();
        String Email = txtEmail.getText();
        String Address = txtAddress.getText();

        SuplayerDto suplayerDto = new SuplayerDto(
                id1,
                Name,
                Phone,
                Email,
                Address
        );

        try {
            String rsp = suplayerModel.saveSuplayer(suplayerDto);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(rsp);
            alert.setHeaderText(" CONFIRMATION ");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
        }


    }
    }

    public void deleteMethod() {
        String id = txtSupplierId.getText();
        try {
            String rsp = suplayerModel.deleteSuplayer(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(rsp);
            alert.setHeaderText(" CONFIRMATION ");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
        }

    }

    public void LodeTableMethod() throws SQLException, ClassNotFoundException {


        ObservableList<SuplayerDto> list = FXCollections.observableArrayList(suplayerModel.getAll());
        colId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("pone_number"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblSuppliers.setItems(list);

    }


   public void updateMethod() throws SQLException, ClassNotFoundException {

       String id = txtSupplierId.getText();
       String Name = txtName.getText();
       String Phone = txtPhone.getText();
       String Email = txtEmail.getText();
       String Address = txtAddress.getText();
       SuplayerDto suplayerDto = new SuplayerDto(
               id,
               Name,
               Phone,
               Email,
               Address
       );
       
       String rsp =suplayerModel.updateSuplayer(suplayerDto);

       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle(rsp);
       alert.show();
       LodeTableMethod();
       clearMethod();
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LodeTableMethod();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong");
            alert.show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong");
            alert.show();
        }
    }

    public void Butten_add_on_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        saveMethod();
        LodeTableMethod();
        clearMethod();
    }

    public void Butten_Update_on_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        updateMethod();
        clearMethod();

    }

    public void butten_on_Delete_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        deleteMethod();
        LodeTableMethod();
        clearMethod();

    }

    public void on_mousClicked(MouseEvent mouseEvent) {

        SuplayerDto suplayerDto = (SuplayerDto) tblSuppliers.getSelectionModel().getSelectedItem();

        if (suplayerDto != null) {
            txtSupplierId.setText(suplayerDto.getSupplier_id());
            txtName.setText(suplayerDto.getSupplier_name());
            txtPhone.setText(suplayerDto.getPone_number());
            txtEmail.setText(suplayerDto.getEmail());
            txtAddress.setText(suplayerDto.getAddress());

            btnAdd.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void clearMethod(){

        txtSupplierId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

        btnAdd.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);


    }


}
