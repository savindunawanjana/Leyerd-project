package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.Modal.ownerLoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDetailsMenuController implements Initializable {


    public Button btnok;
    private ownerLoginModel ownerLoginModel;

    public AnchorPane ancpaneloginownerForRegistetion;//ancpaneloginownerForRegistetion
    public TextField txtfild;

    public UserDetailsMenuController() throws SQLException, ClassNotFoundException {

        ownerLoginModel = new ownerLoginModel();

    }

    public void textfildAction(ActionEvent actionEvent) {

    }

    public void clickbottun(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        login();
    }

    private void navigeteto(String path) {

        try {

            ancpaneloginownerForRegistetion.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));

            parent.prefWidthProperty().bind(ancpaneloginownerForRegistetion.widthProperty());
            parent.prefHeightProperty().bind(ancpaneloginownerForRegistetion.heightProperty());

            ancpaneloginownerForRegistetion.getChildren().add(parent);
        } catch (Exception e) {


            Alert alat = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            e.printStackTrace();


        }


    }

    public void login() throws SQLException, ClassNotFoundException {
        String rspPassword = "null";
        ArrayList<SystemUser> cachobjectList = ownerLoginModel.getuserinformation();
        String textpassword = txtfild.getText();

        for (int i = 0; i < cachobjectList.size(); i++) {

            SystemUser systemUser = cachobjectList.get(i);
            String databases_password = systemUser.getUser_password();

            if (textpassword.equals(databases_password)) {

                rspPassword = "ok";
                navigeteto("/View/UserManage.fxml");

            }

        }

        if (rspPassword == "null") {

            new Alert(Alert.AlertType.ERROR, " this is a Wrong Password ", ButtonType.OK).show();

        }

    }

    public void onDoorPw(MouseEvent mouseEvent) {
        navigeteto("/View/UserDoorPw.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        javafx.application.Platform.runLater(() -> txtfild.requestFocus());
    }

    public void onKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            login();
        }

    }
}
