package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.memberPaymentDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.MemberpaymentModal;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CustomerDetailsMenuController implements Initializable {

    @FXML
    private TableColumn<memberPaymentDto, String> Colam_custormerId;

    @FXML
    private TableColumn<memberPaymentDto, String> Colam_ExpayerDateId;

    @FXML
    private TableColumn<memberPaymentDto, String> Colam_systemuserId;

    @FXML
    private TableColumn<memberPaymentDto, Integer> Colam_ValidnomberofmonthId;

    @FXML
    private TableColumn<memberPaymentDto, String> Colam_paymentDateId;

    @FXML
    private TableColumn<memberPaymentDto, Double> Colam_gevapumudalpramanayaId;

    public AnchorPane ancCustomerDetailsMenu;
    public TableView tableId;
    private MemberpaymentModal  memberPaymentModal= new MemberpaymentModal();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     try {

         ObservableList<memberPaymentDto> data = memberPaymentModal.getAllmethod();
         Colam_custormerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
         Colam_ExpayerDateId.setCellValueFactory(new PropertyValueFactory<>("expire_date"));
         Colam_ValidnomberofmonthId.setCellValueFactory(new PropertyValueFactory<>("valid_number_of_month"));
         Colam_systemuserId.setCellValueFactory(new PropertyValueFactory<>("system_user_role"));
         Colam_paymentDateId.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
         Colam_gevapumudalpramanayaId.setCellValueFactory(new PropertyValueFactory<>("payment_amount"));



        tableId.setItems(data);

        // Row coloring based on condition
        tableId.setRowFactory(tv -> new TableRow<memberPaymentDto>() {

            @Override
            protected void updateItem(memberPaymentDto memberPaymentdto, boolean empty) {
                super.updateItem(memberPaymentdto, empty);

                if (empty || memberPaymentdto == null) {
                    setStyle(""); // Clear style
                    return;
                }



                LocalDate expireDate = LocalDate.parse(memberPaymentdto.getExpire_date());
                LocalDate today = LocalDate.now();


                if (expireDate.isBefore(today) || expireDate.isEqual(today)) {
                    setStyle("-fx-background-color: #b71540;");
                } else {
                    setStyle("");
                }
            }
        });

    }catch (Exception e){

      e.printStackTrace();


    }


    }
    private void navigeteto(String path) {

        try {
            ancCustomerDetailsMenu.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));

            parent.prefWidthProperty().bind(ancCustomerDetailsMenu.widthProperty());
            parent.prefHeightProperty().bind(ancCustomerDetailsMenu.heightProperty());

            ancCustomerDetailsMenu.getChildren().add(parent);
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Page Note Found", ButtonType.OK).show();
            e.printStackTrace();


        }

    }


    public void onDoor_custormerManeg(MouseEvent mouseEvent) {

        navigeteto("/View/CustomerManage.fxml");

    }
}
