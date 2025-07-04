package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class Dashbord implements Initializable {


    public Button btnSystemUserManegment;
    public Button btncustormerManegment;
    public Button btnEmployeeManegment;
    public Button btnsendMassege;
    public Button btnCheneMassegeAoutogenaret;
    public Button Inventory_Management_Id;

    private static final String ACTIVE_STYLE = "-fx-background-color: #1e3799; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-background-radius: 10; "
            + "-fx-border-radius: 10; "
            + "-fx-border-color: #ffffff;";

    private static final String DEFAULT_STYLE = "-fx-background-color: #1e3799; "
            + "-fx-text-fill: #b6ff17; "
            + "-fx-background-radius: 10; "
            + "-fx-border-radius: 10; "
            + "-fx-border-color: #b6ff17;";


    public Button btnviewMonthlyriport;
    public Button btnManegeExsasais;
    public Button btnviewMonthlyriport1;
    public Label lableForTime;
    public Button Btn_Logout;

    private loginManeger loginManeger = new loginManeger();

    public Dashbord() throws SQLException, ClassNotFoundException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);
        LocalDateTime currentDateTime = LocalDateTime.now();

        lableForTime.setText(currentDate + " / " + currentTime);
        navigeteto("/View/UserDetailsMenu.fxml");

    }

    @FXML
    private AnchorPane ancpane1formenupage;

    @FXML
    private AnchorPane ancpaneWrite;


    public void clikButtenSystemUserManegment(ActionEvent actionEvent) throws IOException {

        changeColor();
        navigeteto("/View/UserDetailsMenu.fxml");
        btnSystemUserManegment.setStyle(DEFAULT_STYLE);


    }

    public void clik_on_action_Monthlyriport(ActionEvent actionEvent) {

        changeColor();
        navigeteto("/View/MonthlyRiportpage.fxml");
        btnviewMonthlyriport.setStyle(DEFAULT_STYLE);

    }


    public void clikButtencustormerManegment(ActionEvent actionEvent) {
        changeColor();
        navigeteto("/View/CustomerDetailsMenu.fxml");
        btncustormerManegment.setStyle(DEFAULT_STYLE);
    }

    public void clikButtenEmployeeManegment(ActionEvent actionEvent) {
        changeColor();
        navigeteto("/View/employpageDeatilesView.fxml");
        btnEmployeeManegment.setStyle(DEFAULT_STYLE);
    }

    public void Inventory_Management_On_action(ActionEvent actionEvent) {
        changeColor();
        navigeteto("/View/inventoryPage.fxml");
        Inventory_Management_Id.setStyle(DEFAULT_STYLE);

    }

    public void clickbtnToSend_massege(MouseEvent actionEvent) {

        changeColor();
        navigeteto("/View/sendmassegePageView.fxml");
        btnCheneMassegeAoutogenaret.setStyle(DEFAULT_STYLE);

    }

    public void manegeExsasais_on_action(ActionEvent actionEvent) {

        changeColor();
        navigeteto("/View/manegeExsais.fxml");
        btnManegeExsasais.setStyle(DEFAULT_STYLE);

    }

    private void navigeteto(String path) {
        try {
            ancpaneWrite.getChildren().clear();

            // Load as Parent to avoid class cast issues
            Parent view = FXMLLoader.load(getClass().getResource(path));

            // Let the loaded view fill the AnchorPane
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);

            ancpaneWrite.getChildren().add(view);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();
        }
    }


    private void changeColor() {
         btnSystemUserManegment.setStyle(ACTIVE_STYLE);
                btncustormerManegment.setStyle(ACTIVE_STYLE);
                btnEmployeeManegment.setStyle(ACTIVE_STYLE);
                Inventory_Management_Id.setStyle(ACTIVE_STYLE);
                btnManegeExsasais.setStyle(ACTIVE_STYLE);
                btnviewMonthlyriport.setStyle(ACTIVE_STYLE);
                btnCheneMassegeAoutogenaret.setStyle(ACTIVE_STYLE);

    }


    public void Logout_on_action(ActionEvent actionEvent) throws IOException {
        ancpane1formenupage.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/ownerAndmanegerLoginPage.fxml"));
        ancpane1formenupage.getChildren().add(parent);
    }



}
