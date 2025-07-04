package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.Modal.Registetionmodal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RegitretionController {

    private Registetionmodal registetionmodal;
    private SystemUser systemUser;

    @FXML
    public AnchorPane ancpaneRegistation;
    @FXML
    public TextField textfildUserPassword;

    @FXML
    public TextField textfildUserName;

    @FXML
    public TextField textfildUserRoll;

    @FXML
    public TextField textfildUserNumber;

    @FXML
    public TextField textfildUserid;


    public RegitretionController() {


        registetionmodal = new Registetionmodal();

    }

    public void textfildUseridAction(ActionEvent actionEvent) {

    }

    public void textfildUserPasswordAction(ActionEvent actionEvent) {

//        if(textfildUserPassword==null){
//
//            System.out.println( "textfildUseridAction = null");
//
//        }else {            System.out.println( "textfildUseridAction is not null");
//        }

    }

    public void textfildUserRollAction(ActionEvent actionEvent) {

    }

    public void textfildUserNameAction(ActionEvent actionEvent) {


    }

    public void textfildUserNumberAction(ActionEvent actionEvent) {

    }

    public void createNewMember() throws SQLException, ClassNotFoundException {



        if (textfildUserid.getText() == null || textfildUserid.getText().trim().isEmpty()||textfildUserPassword.getText() == null || textfildUserPassword.getText().trim().isEmpty()||textfildUserRoll.getText() == null || textfildUserRoll.getText().trim().isEmpty()||textfildUserName.getText() == null || textfildUserName.getText().trim().isEmpty()||textfildUserNumber.getText() == null || textfildUserNumber.getText().trim().isEmpty()) {

            System.out.println(" some textfild is null or empty");
            new Alert(Alert.AlertType.ERROR,"some textfild is null or empty", ButtonType.OK).show();


        } else {

            String userId1 = textfildUserid.getText();
            String userPassword1 = textfildUserPassword.getText();
            String userRoll1 = textfildUserRoll.getText();
            String userName1 = textfildUserName.getText();
            String userNumber1 = textfildUserNumber.getText();

            systemUser = new SystemUser(userId1, userPassword1, userRoll1, userName1, userNumber1);
            registetionmodal.savenewMember(systemUser);
        }

    }

    public void clickRegistretionButtenAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        createNewMember();


    }

}
