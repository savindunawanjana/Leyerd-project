package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Modal.MassegeModal;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static edu.lk.ijse.projectgym.demo76promax.Controller.OtogenaretMasegeController.sendeMassegeOtomaticaly;

public class EditOtoJenaretMassege  implements Initializable {
    private MassegeModal massegeModal= new MassegeModal();
    public AnchorPane AncorPane;
    public Button butnSaveMassege;
    public Label lblmasseg;
    public TextField textfildmassege;


    public void Buttensave_on_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String rsp = textfildmassege.getText();
        String rsp2=massegeModal.getmassege();
        massegeModal.deletemethod(rsp2);
        massegeModal.saveMessage(rsp);
        String rsp1=massegeModal.getmassege();
        lblmasseg.setText(rsp1);
    }

    public String getlable() throws SQLException, ClassNotFoundException {

        String rsp1=massegeModal.getmassege();
      return rsp1;


    }

    public void on_key_pressed(KeyEvent keyEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String rsp1=massegeModal.getmassege();
            lblmasseg.setText(rsp1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
