package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Modal.comanPasswordmodal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class loginForsystem {

    public TextField textfildloginpage;
    public AnchorPane ancpaneLoginforsystem;
    private comanPasswordmodal comanPasswordmodal;
    private String cachPassword;

    public loginForsystem() {

        comanPasswordmodal = new comanPasswordmodal();

    }

    public void TextfildLoginpage(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


    }

    public void enterButtenloginpage(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        cachPassword = comanPasswordmodal.getComanPassword(String.valueOf(textfildloginpage));
        String text = textfildloginpage.getText();

        if (text.equals(cachPassword)) {

            ancpaneLoginforsystem.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ownerAndmanegerLoginPage.fxml"));
            ancpaneLoginforsystem.getChildren().add(parent);
        } else {
            new Alert(Alert.AlertType.ERROR, " this is a Wrong Password ", ButtonType.OK).show();
        }

    }

    public void onkeyPresd(KeyEvent keyEvent) throws IOException, SQLException, ClassNotFoundException {


        if (keyEvent.getCode() == KeyCode.ENTER) {

            cachPassword = comanPasswordmodal.getComanPassword(String.valueOf(textfildloginpage));
            String text = textfildloginpage.getText();

            if (text.equals(cachPassword)) {

                ancpaneLoginforsystem.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/View/ownerAndmanegerLoginPage.fxml"));
                ancpaneLoginforsystem.getChildren().add(parent);
            } else {
                new Alert(Alert.AlertType.ERROR, " this is a Wrong Password ", ButtonType.OK).show();
                return;
            }

            ancpaneLoginforsystem.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ownerAndmanegerLoginPage.fxml"));
            ancpaneLoginforsystem.getChildren().add(parent);

        }




    }
}
