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

public class Addcoach implements Initializable {

    private EmployeDto employeDto;
    private Registetionmodal registModel = new Registetionmodal();
    private EmployeModal employeModal = new EmployeModal();


    public AnchorPane anchorpane;
    public TextField textcoachId;
    public TextField textcoachName;
    public TextField coachEmailadressId;
    public TextField coachNumber;
    public Button btnsave;

    private EmployeedataModel employeedataModel= new EmployeedataModel();
    private static final String VALID_STYLE = "-fx-border-color: green; -fx-background-color: #dcdde1;";
    private static final String INVALID_STYLE = "-fx-border-color: red; -fx-background-color: #dcdde1;";
    private static final String DEFAULT_STYLE = "-fx-border-color: #000000; -fx-background-color: #dcdde1;";

    public void buttenONaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        employeedataModel.shouldBeRunThisMethod();
        saveMethod();
        textcoachId.clear();
        textcoachName.clear();
        coachEmailadressId.clear();
        coachNumber.clear();
    }

    public void saveMethod() throws SQLException, ClassNotFoundException {


        if (!validateFields()) {
            new Alert(Alert.AlertType.ERROR, "Please check all fields are valid", ButtonType.OK).show();
            return;
        }

        employeedataModel.shouldBeRunThisMethod();

        LocalDate today = LocalDate.now();
        String date = today.toString();

        String system_user_Id = loginManeger.arry[0];
        String useroll = registModel.getuserRollmethod(system_user_Id);

        employeDto = new EmployeDto(
                textcoachId.getText(),
                textcoachName.getText(),
                coachNumber.getText(),
                useroll,
                date,
                coachEmailadressId.getText());

        employeModal.saveCoach(employeDto);
        new Alert(Alert.AlertType.INFORMATION, "Coach saved successfully!", ButtonType.OK).show();
    }

    public void on_key_presd(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        employeedataModel.shouldBeRunThisMethod();
        System.out.println("Key pressed: " + keyEvent.getCode());

        if(keyEvent.getCode() == KeyCode.ENTER){
    saveMethod();
    textcoachId.clear();
    textcoachName.clear();
    coachEmailadressId.clear();
    coachNumber.clear();
}


    }

    public void on_mouse_click(MouseEvent mouseEvent) {

        navigeteto("/View/DeleteCoch.fxml");

    }

    private void navigeteto(String path) {

        try {
            anchorpane.getChildren().clear();
            AnchorPane parent = FXMLLoader.load(getClass().getResource(path));

            parent.prefWidthProperty().bind(anchorpane.widthProperty());
            parent.prefHeightProperty().bind(anchorpane.heightProperty());

            anchorpane.getChildren().add(parent);
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Page Note Found", ButtonType.OK).show();
            e.printStackTrace();


        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        javafx.application.Platform.runLater(() -> {
            try {
                employeedataModel.shouldBeRunThisMethod();
                setupValidations();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setupValidations() {
        textcoachId.textProperty().addListener((observable, oldValue, newValue) -> validateCoachId());
        textcoachName.textProperty().addListener((observable, oldValue, newValue) -> validateName());
        coachNumber.textProperty().addListener((observable, oldValue, newValue) -> validatePhone());
        coachEmailadressId.textProperty().addListener((observable, oldValue, newValue) -> validateEmail());
    }

    private boolean validateFields() {
        return validateCoachId() && validateName() && validatePhone() && validateEmail();
    }

    private boolean validateCoachId() {
        boolean isValid = textcoachId.getText().matches("C\\d{3}");
        textcoachId.setStyle(isValid ? VALID_STYLE : INVALID_STYLE);
        return isValid;
    }

    private boolean validateName() {
        boolean isValid = textcoachName.getText().matches("[A-Za-z\\s]+");
        textcoachName.setStyle(isValid ? VALID_STYLE : INVALID_STYLE);
        return isValid;
    }

    private boolean validatePhone() {
        boolean isValid = coachNumber.getText().matches("\\d{10}");
        coachNumber.setStyle(isValid ? VALID_STYLE : INVALID_STYLE);
        return isValid;
    }

    private boolean validateEmail() {
        boolean isValid = coachEmailadressId.getText().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        coachEmailadressId.setStyle(isValid ? VALID_STYLE : INVALID_STYLE);
        return isValid;
    }
}
