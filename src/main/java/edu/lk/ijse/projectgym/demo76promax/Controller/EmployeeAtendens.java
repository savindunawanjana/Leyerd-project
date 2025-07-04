package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeeDataDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeedataModel;
import edu.lk.ijse.projectgym.demo76promax.Util.CoachScheduleGenerator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAtendens implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private TableView<EmployeeDataDto> tableViewLeft;

    @FXML
    private TableColumn<EmployeeDataDto, String> colemployeeIdleft;

    @FXML
    private TableColumn<EmployeeDataDto, String> colemployeRollleft;

    @FXML
    private TableColumn<EmployeeDataDto, String> colemployeeNameleft;

    @FXML
    private TableView<EmployeeDataDto> tableWrite;

    @FXML
    private TableColumn<EmployeeDataDto, String> colWrite;

    @FXML
    private TextField textEmployeeId;

    @FXML
    private Button btnSave;

    private String id1 = "C001";
    private String id2 = "C002";

    private EmployeedataModel employeedataModel = new EmployeedataModel();


    public void prosesMethod() throws SQLException, ClassNotFoundException {
        LocalDate today = LocalDate.now();
        int dayOfYear = today.getDayOfYear();

        List<String> schedule = CoachScheduleGenerator.generateSchedule(365);

        if (dayOfYear <= schedule.size()) {
            String todaySchedule = schedule.get(dayOfYear - 1);

            String[] parts = todaySchedule.split(",");
            String coach1 = parts[0].trim();
            id1 = coach1;
            String coach2 = parts[1].trim();
            id2 = coach2;

        } else {
            System.out.println("No schedule found for today.");
        }
    }

    public void lodeLeftTable() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeDataDto> observableList = employeedataModel.SerchMethod(id1, id2);

        System.out.println("List size: " + (observableList != null ? observableList.size() : "null"));
        if (observableList != null) {
            for (EmployeeDataDto dto : observableList) {
                System.out.println(dto);
            }
        }

        colemployeeIdleft.setCellValueFactory(new PropertyValueFactory<>("id"));
        colemployeRollleft.setCellValueFactory(new PropertyValueFactory<>("role"));
        colemployeeNameleft.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewLeft.setItems(observableList);
    }


    private void setupTableWrite() {

        colWrite.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void Save_on_action(javafx.event.ActionEvent event) {
        String employeeId = textEmployeeId.getText().trim();

        if (employeeId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter an Employee ID.").show();
            return;
        }

        try {
            EmployeeDataDto dto = employeedataModel.findById(employeeId);

            if (dto != null) {

                boolean exists = tableWrite.getItems().stream()
                        .anyMatch(emp -> emp.getId().equals(dto.getId()));

                if (!exists) {
                    tableWrite.getItems().add(dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Employee already marked present.").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee not found.").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error while fetching employee.").show();
        }

        textEmployeeId.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            prosesMethod();
            lodeLeftTable();
            setupTableWrite();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong during initialization.").show();
        }

    }


    public void on_mouse_clicked(MouseEvent mouseEvent) {



    }
}
