package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.Modal.ownerLoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class loginOwner implements Initializable {

    public Button btnok;
    private String rspPassword = "null";
    private ownerLoginModel ownerLoginModel;
    public AnchorPane ancpaneloginowner;
    public TextField TFSBuild;

    public loginOwner() throws SQLException, ClassNotFoundException {
        ownerLoginModel = new ownerLoginModel();
    }

    public void textfildAction(ActionEvent actionEvent) {
    }

    public void clickbottun(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<SystemUser> cachobjectList = ownerLoginModel.getuserinformation();
        String textpassword = TFSBuild.getText();
        loginManeger.arry[0] = textpassword;

        for (int i = 0; i < cachobjectList.size(); i++) {

            SystemUser systemUser = cachobjectList.get(i);
            String databases_password = systemUser.getUser_password();

            if (textpassword.equals(databases_password)) {
                rspPassword = "ok";
                ancpaneloginowner.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashbord.fxml"));
                ancpaneloginowner.getChildren().add(parent);
            }
        }

        if (rspPassword == "null") {
            new Alert(Alert.AlertType.ERROR, " this is a Wrong Password ", ButtonType.OK).show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        javafx.application.Platform.runLater(() -> TFSBuild.requestFocus());

    }


    public void onkeyPresd(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException {

        ArrayList<SystemUser> cachobjectList = ownerLoginModel.getuserinformation();
        String textpassword = TFSBuild.getText();
        loginManeger.arry[0] = textpassword;

        for (int i = 0; i < cachobjectList.size(); i++) {

            SystemUser systemUser = cachobjectList.get(i);
            String databases_password = systemUser.getUser_password();


            if (keyEvent.getCode() == KeyCode.ENTER) {

                if (textpassword.equals(databases_password)) {
                    rspPassword = "ok";
                    ancpaneloginowner.getChildren().clear();
                    Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashbord.fxml"));
                    ancpaneloginowner.getChildren().add(parent);
                    return;
                }else {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Unsuccessfully");
                    alert.setHeaderText("Pleas enter valid password ");
                    alert.show();
                    return;
                }

            }


        }

    }
}