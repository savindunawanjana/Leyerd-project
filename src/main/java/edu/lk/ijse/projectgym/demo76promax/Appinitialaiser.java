package edu.lk.ijse.projectgym.demo76promax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import static edu.lk.ijse.projectgym.demo76promax.Controller.OtogenaretMasegeController.sendeMassegeOtomaticaly;

public class Appinitialaiser extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForsystems.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        //set title to window
        stage.setScene(scene);
        stage.show();

    }

}
