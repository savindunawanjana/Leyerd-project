package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.comanPasswordobject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import edu.lk.ijse.projectgym.demo76promax.Modal.comanPasswordmodal;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDoorPwController implements Initializable {

    public AnchorPane ancpaneCommanpassword;
    public Button btnSave;
    @FXML
    private TableColumn<comanPasswordobject, String> paswordColam;
    @FXML
    private TextField textCommanpassword;

    @FXML
    private TableView<comanPasswordobject> idVewTable;

    public void cachCommanpassword(ActionEvent actionEvent) {

    }

    public void clickSaveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (textCommanpassword.getText() == null || textCommanpassword.getText().trim().isEmpty()) {
            Alert alat = new Alert(Alert.AlertType.INFORMATION);
            alat.setContentText("textfildUserPassword is empty");
            alat.show();

        } else {
            String textfild = textCommanpassword.getText();
            String cach = comanPasswordmodal.setComanPassword(textfild);
            Alert alat = new Alert(Alert.AlertType.INFORMATION);
            alat.setContentText(cach);
            alat.show();
        }

    }

    public void lodetable() throws SQLException, ClassNotFoundException {

        ObservableList<comanPasswordobject> list = comanPasswordmodal.getComanPasswordFromDatabases();
        paswordColam.setCellValueFactory(new PropertyValueFactory<>("comanPassword"));
        idVewTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("comanPass initialize");

        try {
            lodetable();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alat = new Alert(Alert.AlertType.ERROR);
            alat.setContentText(e.getMessage());
            alat.setHeaderText(e.getMessage());
            alat.show();
        }

    }
}
