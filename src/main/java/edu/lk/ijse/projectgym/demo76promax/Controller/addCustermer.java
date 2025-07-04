package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class addCustermer {
    private CustormerModel custormerModel = new CustormerModel();

    private CustermerDto custermer;

    @FXML
    private TextField TextCid;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtBirthdayMonth;

    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRegisterFees;
    @FXML
    private AnchorPane ancpaneAddcustormer;

    @FXML
    private Button btnsave;


    public void TextCidAction(ActionEvent actionEvent) {

    }

    public void txtCustomerNameAction(ActionEvent actionEvent) {

    }

    public void txtCustomerAddressAction(ActionEvent actionEvent) {

    }

    public void txtContactNumberCtion(ActionEvent actionEvent) {


    }

    public void txtBirthdayMonthCtion(ActionEvent actionEvent) {

    }

    public void txtWeightAction(ActionEvent actionEvent) {


    }

    public void txtEmailAction(ActionEvent actionEvent) {


    }

    public void txtRegisterFeesAction(ActionEvent actionEvent) {


    }

    public void clickBtnToSaveCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        savemethadeFromController();

        textfildclearmethod();
        btnsave.setDisable(true);


    }

//====================================================================================================================================================================

    public void savemethadeFromController() throws SQLException, ClassNotFoundException {
        try {
            custermer = new CustermerDto(
                    TextCid.getText(),
                    txtCustomerName.getText(),
                    txtCustomerAddress.getText(),
                    txtContactNumber.getText(),
                    txtBirthdayMonth.getText(),
                    Integer.parseInt(txtWeight.getText()),
                    txtEmail.getText(),
                    Double.parseDouble(txtRegisterFees.getText())

            );

            System.out.println("Customer created successfully.");


        } catch (NumberFormatException e) {


            System.out.println("Invalid number format in weight or register fee field.");

        }

        custormerModel.saveCustomer(custermer);

    }


    public void textfildclearmethod (){

        TextCid.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtContactNumber.clear();
        txtBirthdayMonth.clear();
        txtWeight.clear();
        txtEmail.clear();
        TextCid.clear();
        txtRegisterFees.clear();

    }




}