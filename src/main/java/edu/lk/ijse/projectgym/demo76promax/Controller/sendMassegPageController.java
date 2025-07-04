package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class sendMassegPageController {
    public VBox sendShedulToCustormerId;
    public VBox editAoutoGenareteMassegeId;
    public AnchorPane AnchorPane; // This is your container where views are loaded
    public VBox sendmonthlymassegeId1;

    public void on_sendShedulToCustormer_mouse_clicked(MouseEvent mouseEvent) throws IOException {
        navigateTo("/View/sendShedulToCustormer.fxml");
    }

    public void on_edit_aouto_genaret_massege_mouse_clicked(MouseEvent mouseEvent) {

        navigateTo("/View/editOtoJenaretMassege.fxml");

    }

    public void navigateTo(String path) {
        try {
            AnchorPane.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(AnchorPane.widthProperty());
            anchorPane.prefHeightProperty().bind(AnchorPane.heightProperty());
            AnchorPane.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    public void on_sendMonthlymassege_mouse_clicked(MouseEvent mouseEvent) {
        navigateTo("/View/monthlyMessagesendPage.fxml");

    }
}
