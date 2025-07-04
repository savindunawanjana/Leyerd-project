package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import edu.lk.ijse.projectgym.demo76promax.Modal.ownerLoginModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ownerAndmanegerLoginPage {

    public Button bottonOwnerlogin;
    @FXML
    private AnchorPane ancpanesecondLogin;


    @FXML
    void clickButtonOwnerdotoDashbordpage(ActionEvent actionEvent) throws IOException {

        ancpanesecondLogin.getChildren().clear();

        Parent rst = FXMLLoader.load(getClass().getResource("/View/loginOwnerView.fxml"));
        ancpanesecondLogin.getChildren().add(rst);


    }

    @FXML
    void clickButtonManegerdotoDashbordpage(ActionEvent actionEvent) throws IOException {

        ancpanesecondLogin.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginManeger.fxml"));
        ancpanesecondLogin.getChildren().add(parent);


    }

    @FXML
    void clickButtonRegitretiondotoDashbordpage(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {


        ancpanesecondLogin.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginOwnerForRegistetion.fxml"));
        ancpanesecondLogin.getChildren().add(parent);


    }

    public void clickBacknuttontoback(ActionEvent actionEvent) throws IOException {

        ancpanesecondLogin.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForsystems.fxml"));
        ancpanesecondLogin.getChildren().add(parent);


    }


}
