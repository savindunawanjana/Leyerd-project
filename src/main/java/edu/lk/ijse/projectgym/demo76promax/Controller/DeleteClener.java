package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.DeleteClenerDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.DeleteClenerModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeModal;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeedataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DeleteClener implements Initializable {
    private DeleteClenerModel model = new DeleteClenerModel();
    private DeleteClenerDto deleteClenerDto;
    public AnchorPane anchorpaneDeletCleaner;
    public TableView tableView;
    public TextField txtRisonId;
    private EmployeModal employeModal = new EmployeModal();

    private final String idPattern = "^W.*$";

    @FXML
    private TableColumn<EmployeDto, String> colClenerId;

    @FXML
    private TableColumn<EmployeDto, String> colClenerName;


    public TextField cleanerId;
    public Button btnsave;
    private EmployeModal empModal = new EmployeModal();
    private EmployeedataModel employeedataModel = new EmployeedataModel();

    public void saveButten_On_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        employeedataModel.shouldBeRunThisMethod();
        deleteClener();
        loadCleanerTable();
        cleanerId.clear();
        txtRisonId.clear();
    }

    public void deleteClener() throws SQLException, ClassNotFoundException {
        try {
            String id = cleanerId.getText().trim();
            String reason = txtRisonId.getText().trim();

            cleanerId.setStyle("-fx-border-color: green");
            txtRisonId.setStyle("-fx-border-color: green");

            boolean isValidId = id.matches(idPattern);
            boolean isValidReason = !reason.isEmpty();

            if (!isValidId) {
                cleanerId.setStyle("-fx-border-color: red");
            }
            if (!isValidReason) {
                txtRisonId.setStyle("-fx-border-color: red");
            }

            if (!isValidId || !isValidReason) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid Input!");
                alert.setContentText("Please check the highlighted fields");
                alert.show();
                return;
            }

            String name = employeModal.findNameByIds(id);
            if (name == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Worker ID");
                alert.setContentText("No worker found with the given ID");
                alert.show();
                return;
            }

            LocalDate todayDate = LocalDate.now();
            String date = todayDate.toString();

            deleteClenerDto = new DeleteClenerDto(
                    id,
                    name,
                    date,
                    loginManeger.arry[0],
                    reason
            );

            boolean saved = model.saveDeleteCleaner(deleteClenerDto);
            Alert alert;
            if (saved) {
                employeModal.deleteWorker(id);
                alert = new Alert(Alert.AlertType.INFORMATION, "Delete successful.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Delete failed.");
            }
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    public void loadCleanerTable() throws SQLException, ClassNotFoundException {
        employeedataModel.shouldBeRunThisMethod();
        ObservableList<EmployeDto> cleaner = empModal.getAllWorkers();
        colClenerId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colClenerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableView.setItems(cleaner);
    }

    public void on_mouse_back_page(MouseEvent mouseEvent) {
        navigeteto("/View/Adddcleaner.fxml");
    }

    private void navigeteto(String path) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCleanerTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void on_key_presd(KeyEvent keyEvent) {
    }
}