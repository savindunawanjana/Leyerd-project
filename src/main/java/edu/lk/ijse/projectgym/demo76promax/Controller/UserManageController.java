package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.Modal.Registetionmodal;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserManageController implements Initializable {

    private final String phonePattern = "^\\d{10}$";


    private final Registetionmodal registetionmodal = new Registetionmodal();
    private final SystemUser systemUser = new SystemUser();
    @FXML
    private AnchorPane ancPaneSystemUsermanegmentView;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SystemUser, String> colId;

    @FXML
    private TableColumn<SystemUser, String> colNumber;

    @FXML
    private TableColumn<SystemUser, String> colPassword;

    @FXML
    private TableColumn<SystemUser, String> colRole;

    @FXML
    private TableColumn<SystemUser, String> colUsername;

    @FXML
    private Label lblId;

    @FXML
    private TableView<SystemUser> tblUser;

    @FXML
    private TextField textNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRoll;

    @FXML
    private TextField txtUserPassword;

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblId.getText();

        if (!registetionmodal.isUserIdExists(id)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ID is not available");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this?",
                ButtonType.OK, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean isDelete = registetionmodal.deleteMember(id);
                    if (isDelete) {
                        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                        infoAlert.setContentText("Delete successful");
                        infoAlert.show();
                        resetPage();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("Error occurred while deleting: " + e.getMessage());
                    errorAlert.show();
                }
            }
        });
    }


    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        resetPage();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String phone = textNumber.getText();
        boolean isValidPhone = phone.matches(phonePattern);
        textNumber.setStyle("-fx-border-color: #acb3ac;");

        if (!isValidPhone) {
            textNumber.setStyle("-fx-border-color: red;");
        }

        if (isValidPhone){

            SystemUser systemUser = new SystemUser(
                    lblId.getText(),
                    txtUserPassword.getText(),
                    txtRoll.getText(),
                    txtName.getText(),
                    textNumber.getText()
            );


        try {

            String cach = registetionmodal.savenewMember(systemUser);
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setContentText(cach);
            loadTable();
            resetPage();

        } catch (Exception e) {

            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        }
    }

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (txtName.getText().isEmpty() || textNumber.getText().isEmpty() || txtRoll.getText().isEmpty() || txtUserPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill in all the fields.");
            alert.show();
            return;
        }

        SystemUser systemUser2 = new SystemUser(
                lblId.getText(),
                txtUserPassword.getText(),
                txtRoll.getText(),
                txtName.getText(),
                textNumber.getText()
        );

        try {
            boolean isUpdated = registetionmodal.updateuserMethod(systemUser2);

            if (isUpdated) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setContentText("Update successful");
                informationAlert.show();
                resetPage();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Failed to update the user.");
                errorAlert.show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error occurred while updating: " + e.getMessage());
            errorAlert.show();
        }
    }



    @FXML
    void onClickTable(MouseEvent event) {
        SystemUser selectedItem = tblUser.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getUser_Id());
            txtName.setText(selectedItem.getUser_Name());
            txtUserPassword.setText(selectedItem.getUser_password());
            txtRoll.setText(selectedItem.getUser_Roll());
            textNumber.setText(selectedItem.getPon_number());

            btnSave.setDisable(true);

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }

    @FXML
    void txtNameChange(KeyEvent event) {}

    public void loadTable() throws SQLException, ClassNotFoundException {

        ObservableList<SystemUser> cach = new Registetionmodal().getAllmethod();

        colId.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("User_password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("user_Roll"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("user_Name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("pon_number"));

        tblUser.setItems(cach);


    }
    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        loadTable();

//        Show or hide
       btnDelete.setVisible(true);
       btnUpdate.setVisible(true);
       btnSave.setVisible(true);

//        disabled
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

//        enabled
        btnSave.setDisable(false);

        txtName.setText("");
        textNumber.setText("");
        txtRoll.setText("");
        txtUserPassword.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadNextId();
            loadTable();
            resetPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);

        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = registetionmodal.getNextId();
        lblId.setText(nextId);
    }





}
