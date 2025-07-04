package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeModal;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeedataModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.Registetionmodal;
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

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Addcleaner implements Initializable {
    private EmployeDto employeDto;
    private Registetionmodal registModel = new Registetionmodal();
    private EmployeModal employeModal = new EmployeModal();

    private final String idPattern = "^W.*$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    public AnchorPane Anchorpane;
    public TextField cleanerId;
    public TextField emailaddress;
    public TextField poneNumber;
    public TextField cleaner_name;
    public Button butten;

    private EmployeedataModel employeedataModel = new EmployeedataModel();

    public void saveMethod() throws SQLException, ClassNotFoundException {
        String id = cleanerId.getText();
        String email = emailaddress.getText();
        String phone = poneNumber.getText();
        String name = cleaner_name.getText();

        boolean isvalidid = id.matches(idPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidName = !name.isEmpty();

        cleanerId.setStyle("-fx-border-color: green;");
        emailaddress.setStyle("-fx-border-color: green;");
        poneNumber.setStyle("-fx-border-color: green;");
        cleaner_name.setStyle("-fx-border-color: green;");

        if (!isvalidid) {
            cleanerId.setStyle("-fx-border-color: red;");
        }
        if (!isValidEmail) {
            emailaddress.setStyle("-fx-border-color: red;");
        }
        if (!isValidPhone) {
            poneNumber.setStyle("-fx-border-color: red;");
        }
        if (!isValidName) {
            cleaner_name.setStyle("-fx-border-color: red;");
        }

        employeedataModel.shouldBeRunThisMethod();
        String system_user_Id = loginManeger.arry[0];
        String useroll = registModel.getuserRollmethod(system_user_Id);
        LocalDate today = LocalDate.now();
        String date = today.toString();

        employeDto = new EmployeDto(
                cleanerId.getText(),
                cleaner_name.getText(),
                poneNumber.getText(),
                useroll,
                date,
                emailaddress.getText());

        if (isvalidid && isValidEmail && isValidPhone && isValidName) {
            employeModal.saveWorker(employeDto);
            new Alert(Alert.AlertType.INFORMATION, "Data saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please check the highlighted fields").show();
        }
    }

    public void butten_on_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        employeedataModel.shouldBeRunThisMethod();
        saveMethod();
        cleanerId.clear();
        emailaddress.clear();
        poneNumber.clear();
        cleaner_name.clear();
    }

    public void on_mouse_click(MouseEvent mouseEvent) {
        navigeteto("/View/DeleteClener.fxml");
    }

    private void navigeteto(String path) {
        try {
            Anchorpane.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));
            parent.prefWidthProperty().bind(Anchorpane.widthProperty());
            parent.prefHeightProperty().bind(Anchorpane.heightProperty());
            Anchorpane.getChildren().add(parent);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page Note Found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    public void on_key_presd(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        employeedataModel.shouldBeRunThisMethod();
        System.out.println("Key pressed: " + keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.ENTER) {
            saveMethod();
            cleanerId.clear();
            emailaddress.clear();
            poneNumber.clear();
            cleaner_name.clear();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        javafx.application.Platform.runLater(() -> {
            try {
                employeedataModel.shouldBeRunThisMethod();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}