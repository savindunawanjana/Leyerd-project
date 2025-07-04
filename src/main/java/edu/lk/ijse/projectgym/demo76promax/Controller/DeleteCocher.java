package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeModal;
import edu.lk.ijse.projectgym.demo76promax.Modal.EmployeedataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteCocher implements Initializable {

    public Label lblCoachCount;
    public TableView tblView;
    @FXML
    private TableColumn<EmployeDto, String> colCochId;

    @FXML
    private TableColumn<EmployeDto, String> ColCoachName;


    private EmployeModal employeModal = new EmployeModal();
    public AnchorPane AnchorPane;
    public Button buttenId;
    public TextField textFildId;
    private EmployeedataModel employeedataModel = new EmployeedataModel();

    public void delete_butten_on_action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        employeedataModel.shouldBeRunThisMethod();
        employeModal.deleteCoach(textFildId.getText());
        int rsp = employeModal.setlableCoachCount();
        lblCoachCount.setText(rsp + "");
        textFildId.clear();
        loadCocherTable();

    }

    public void loadCocherTable() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeDto> cleaner = employeModal.getAllCoaches();
        colCochId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        ColCoachName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblView.setItems(cleaner);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            int rsp = employeModal.setlableCoachCount();
            lblCoachCount.setText(rsp + "");
            loadCocherTable();
        } catch (SQLException e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
        }
    }
}
