package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class employpageDeatilesController  implements Initializable {

    public HBox hboxCleanerManege;
    public HBox hboxCoachManege;
    public HBox hboxPaymentManege;
    public HBox hboxleaveManegement;
    public HBox hboxEmployeeDeatelsManegement;
    public HBox hboxEmployeeAtendans;
    public HBox hboxCleanerManege1;
    @FXML
    private AnchorPane ancManagement;

    @FXML
    private AnchorPane anchorpaneOne;

    @FXML
    void onClenerManage(MouseEvent event) {

        navigeteto("/View/Adddcleaner.fxml");


    }

    @FXML
    void onCoacherManage(MouseEvent event) {

        navigeteto("/View/Addcocher2.fxml");

    }

    @FXML
    void onEmployeePayments(MouseEvent event) {

       navigeteto("/View/EmployePayments.fxml");

    }

    @FXML
    void onLeaveManagement(MouseEvent event) {

       // navigeteto();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        navigeteto("/View/EmployPageView.fxml");


    }

    private void navigeteto(String path) {

        try {
            ancManagement.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));

            parent.prefWidthProperty().bind(ancManagement.widthProperty());
            parent.prefHeightProperty().bind(ancManagement.heightProperty());

            ancManagement.getChildren().add(parent);
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Page Note Found", ButtonType.OK).show();
            e.printStackTrace();


        }


    }


    public void onLeaveCoachManagement(MouseEvent mouseEvent) throws IOException {

        ancManagement.getChildren().clear();
        AnchorPane parent = FXMLLoader.load(getClass().getResource("/View/coachLive.fxml"));

        parent.prefWidthProperty().bind(ancManagement.widthProperty());
        parent.prefHeightProperty().bind(ancManagement.heightProperty());

        ancManagement.getChildren().add(parent);

    }

    public void onClenerLiveManage(MouseEvent mouseEvent) throws IOException {
        ancManagement.getChildren().clear();
        AnchorPane parent = FXMLLoader.load(getClass().getResource("/View/clenerLive.fxml"));

        parent.prefWidthProperty().bind(ancManagement.widthProperty());
        parent.prefHeightProperty().bind(ancManagement.heightProperty());

        ancManagement.getChildren().add(parent);

    }

    public void on_EmployeeDeatelsManegement(MouseEvent mouseEvent) {


        navigeteto("/View/Employeedatafromtable.fxml");

    }

    public void On_EmployeeAtendans(MouseEvent mouseEvent) {

        navigeteto("/View/EmployeeAtendens.fxml");


    }
}
