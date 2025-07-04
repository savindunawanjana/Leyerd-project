package edu.lk.ijse.projectgym.demo76promax.Controller;


import edu.lk.ijse.projectgym.demo76promax.Dtos.ExsaisDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.ExsaisTm;
import edu.lk.ijse.projectgym.demo76promax.Modal.ExsasaisModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.Registetionmodal;
import javafx.collections.FXCollections;
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

public class ManegeExsaisController implements Initializable {

    private Registetionmodal registetionmodal = new Registetionmodal();
    private ExsasaisModel exsasaisModel = new ExsasaisModel();
    public AnchorPane AnchorPane;
    public TextField txtExercisId;
    public TextField txtCategory;
    public TextField textexsaisName;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView tblExercises;

    @FXML
    private TableColumn<ExsaisTm,Integer>colId;

    @FXML
    private TableColumn<ExsaisTm,String>colName;

    @FXML
    private TableColumn<ExsaisTm,String>colSystemUser;

    @FXML
    private TableColumn<ExsaisTm,String>colCategory;

    @FXML
    private TableColumn<ExsaisTm,String>colAddedDate;

    public ManegeExsaisController() throws SQLException, ClassNotFoundException {



    }

    public void lodeMethod() throws SQLException, ClassNotFoundException {

        ObservableList<ExsaisTm> observableList = FXCollections.observableArrayList(exsasaisModel.getAllmethod());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("exsaisName"));
        colSystemUser.setCellValueFactory(new PropertyValueFactory<>("systemUserRoll"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("exsaisCategory"));
        colAddedDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tblExercises.setItems(observableList);

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        try {
            String cach = loginManeger.arry[0];
            String id = registetionmodal.getuserId(cach);
            System.out.println(id);
            ExsaisDto dto = new ExsaisDto(Integer.parseInt(txtExercisId.getText()), textexsaisName.getText(), txtCategory.getText(),id);

            String rsp = exsasaisModel.saveMethod(dto);
            lodeMethod();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rsp);
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            String cach = loginManeger.arry[0];
            String id = registetionmodal.getuserId(cach);
            System.out.println(id);
            ExsaisDto dto = new ExsaisDto(Integer.parseInt(txtExercisId.getText()), textexsaisName.getText(), txtCategory.getText(), id);
            String rsp = exsasaisModel.updateMethod(dto);
            lodeMethod();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rsp);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            String id = txtExercisId.getText();
            String rsp = exsasaisModel.deleteMethod(id);
            lodeMethod();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rsp);
            alert.show();


        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

    public void On_mouse_clicked(MouseEvent mouseEvent) {

        //==================================importation=================
        ExsaisTm tm = (ExsaisTm) tblExercises.getSelectionModel().getSelectedItem();
        if (tm != null) {
            txtExercisId.setText(String.valueOf(tm.getId()));
            textexsaisName.setText(tm.getExsaisName());
            txtCategory.setText(tm.getExsaisCategory());
        }

        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        //==================================importation=================

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);



        try {
            lodeMethod();
        } catch (SQLException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

    }
}
