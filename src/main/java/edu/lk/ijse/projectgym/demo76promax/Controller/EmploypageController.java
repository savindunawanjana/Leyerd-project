package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeModal;
import edu.lk.ijse.projectgym.demo76promax.Modal.Registetionmodal;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmploypageController  implements Initializable {

    public AnchorPane anchorpane;
    public Label lblCleanersCount1;
    public Label lblCochesCount1;


    @FXML
    private TableColumn<EmployeDto, String> colamCleanerId;

    @FXML
    private TableColumn<EmployeDto, String> colamCleanerName;

    @FXML
    private TableColumn<EmployeDto, String> colamCleanerNumber;

    @FXML
    private TableColumn<EmployeDto, String> colamCleanerSystemuser;

    @FXML
    private TableColumn<EmployeDto, String> colamCleanerEmail;

    @FXML
    private TableView<EmployeDto> tableCleaners;

    //============================================



    @FXML
    private TableView<EmployeDto> tableCoach;

    @FXML
    private TableColumn<EmployeDto, String> colamCochId;

    @FXML
    private TableColumn<EmployeDto, String> colamCoachSystemUser;

    @FXML
    private TableColumn<EmployeDto, String> colamCochEmail;

    @FXML
    private TableColumn<EmployeDto, String> colamCoachName;

    @FXML
    private TableColumn<EmployeDto, String> colamCochNumber;
    private EmployeModal empModal=new EmployeModal();


    public void loadCoachTable() throws SQLException, ClassNotFoundException {

        ObservableList<EmployeDto> coaches = empModal.getAllCoaches();

        colamCochId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colamCoachName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colamCochNumber.setCellValueFactory(new PropertyValueFactory<>("cnumber"));
        colamCoachSystemUser.setCellValueFactory(new PropertyValueFactory<>("systemUserId"));
        colamCochEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableCoach.setItems(coaches);

    }

    public void loadCleanerTable() throws SQLException, ClassNotFoundException {

        ObservableList<EmployeDto>cleaner = empModal.getAllWorkers();

        colamCleanerId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colamCleanerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colamCleanerNumber.setCellValueFactory(new PropertyValueFactory<>("cnumber"));
        colamCleanerSystemuser.setCellValueFactory(new PropertyValueFactory<>("systemUserId"));
        colamCleanerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableCleaners.setItems(cleaner);


    }
     public void setEmployeeCountTotheLable() throws SQLException, ClassNotFoundException {


         lblCochesCount1.setText(String.valueOf(empModal.setlableCoachCount()));
         lblCleanersCount1.setText(String.valueOf(empModal.setlableCleanerCount()));




     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            loadCoachTable();
            loadCleanerTable();
            setEmployeeCountTotheLable();

        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error  " + " 👈");
            alert.setTitle(e.getMessage());
            alert.showAndWait();


        }


    }


}
