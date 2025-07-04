package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class EmployeeManagement {

    // FXML links to the AnchorPanes and MenuItems
    public AnchorPane Anchorpane;
    public MenuItem add_Cleaners;
    public MenuItem Delete_Cleaners;
    public MenuItem Employe_Lives;
    public MenuItem delete_coach;
    public MenuItem add_coach;
    public AnchorPane secoundAnchorpane;
    public MenuItem Coach_Lives_id;
    public MenuItem Cleaners_Lives_id;

    // Handle Add Cleaners action

    @FXML
    public void add_Cleaners_on_action(ActionEvent actionEvent) {

        navigetion("/View/Adddcleaner.fxml");


    }

    public void Delete_Cleaners_on_action(ActionEvent actionEvent) {

        navigetion("/View/DeleteClener.fxml");

    }


    public void add_coach_on_action(ActionEvent actionEvent) {

        navigetion("/View/Addcocher2.fxml");
    }

    public void delete_coach_on_action(ActionEvent actionEvent) {

        navigetion("/View/DeleteCoch.fxml");

    }

    public void Coaches_Lives_on_action(ActionEvent actionEvent) {

        navigetion("/View/coachLive.fxml");

    }

    public void Cleaners_Lives_on_action(ActionEvent actionEvent) {

        navigetion("/View/clenerLive.fxml");

    }

    public void Payment_on_action(ActionEvent actionEvent) {

        navigetion("/View/EmployePayments.fxml");


    }


    // Navigation to different views based on the provided path
    public void navigetion(String a){


        try {
            secoundAnchorpane.getChildren().clear();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(a)));
            secoundAnchorpane.getChildren().add(parent);

        }catch (Exception e){

            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();

        }


    }



}
