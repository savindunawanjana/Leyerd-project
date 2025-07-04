package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class InventoryController {
    public AnchorPane anchorpaneOne;
    public HBox btnPlaceOder;
    public HBox btnAddItems;
    public AnchorPane anchorpaneTwo;

    public void on_mouse_action_for_addSuplayer(MouseEvent mouseEvent) {

navigeteto("/View/addsuplayersView.fxml");

    }

    public void On_mouse_action_place_oder(MouseEvent mouseEvent) {

        navigeteto("/View/oderPageFxml.fxml");

    }

    public void on_mouse_Add_items(MouseEvent mouseEvent) {


        navigeteto("/View/addItemsView.fxml");

    }


    private void navigeteto(String path) {

        try {

            anchorpaneTwo.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));

            parent.prefWidthProperty().bind(anchorpaneTwo.widthProperty());
            parent.prefHeightProperty().bind(anchorpaneTwo.heightProperty());

            anchorpaneTwo.getChildren().add(parent);
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();


        }

    }



}
