package edu.lk.ijse.projectgym.demo76promax.Controller;


import com.mysql.cj.protocol.Message;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.ExsaisTm;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.SelectExsaisTm;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel;
import edu.lk.ijse.projectgym.demo76promax.Modal.ExsasaisModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class SendShedulToCustormerController implements Initializable {

    public TextField txtEmail;
    public TextField txtCustomerName;
    @FXML
    private ChoiceBox<String> choisBoxId;
    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<SelectExsaisTm> tblSchedule1;
    @FXML
    private TableColumn<SelectExsaisTm, String> colReps1;
    @FXML
    private TableColumn<SelectExsaisTm, String> colCategory1;
    @FXML
    private TableColumn<SelectExsaisTm, String> colExercisename;
    @FXML
    private TableColumn<SelectExsaisTm, Button> colAction;

    @FXML
    private TableView<ExsaisTm> tblChoos;
    @FXML
    private TableColumn<ExsaisTm, String> colExercisenameChoss;
    @FXML
    private TableColumn<ExsaisTm, String> colExerciseCatorgaryChoss;

    private ObservableList<SelectExsaisTm> scheduleList = FXCollections.observableArrayList();

    private final ExsasaisModel exsasaisModel = new ExsasaisModel();
    private final CustormerModel custormerModel = new CustormerModel();

    public SendShedulToCustormerController() throws SQLException, ClassNotFoundException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            List<String> customerIds = custormerModel.getAllCustomerIds();
            choisBoxId.getItems().addAll(customerIds);


            choisBoxId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    try {
                        String name = custormerModel.findNameById(newVal);
                        String email = custormerModel.findEmailById(newVal);
                        System.out.println(name);
                        System.out.println(email);

                        txtCustomerName.setText(name);
                        txtEmail.setText(email);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            lodeChoosTable();

        } catch (Exception e) {
            e.printStackTrace();
        }


        colExercisename.setCellValueFactory(new PropertyValueFactory<>("exsaisName"));
        colCategory1.setCellValueFactory(new PropertyValueFactory<>("exsaisCategory"));
        colReps1.setCellValueFactory(new PropertyValueFactory<>("reps"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));


        colReps1.setCellFactory(TextFieldTableCell.forTableColumn());
        colReps1.setOnEditCommit(event -> {
            SelectExsaisTm tm = event.getRowValue();
            tm.setReps(event.getNewValue());
        });

        tblSchedule1.setEditable(true);
    }

    public void lodeChoosTable() throws SQLException, ClassNotFoundException {
        ObservableList<ExsaisTm> observableList = FXCollections.observableArrayList(exsasaisModel.getAllmethod());

        colExercisenameChoss.setCellValueFactory(new PropertyValueFactory<>("exsaisName"));
        colExerciseCatorgaryChoss.setCellValueFactory(new PropertyValueFactory<>("exsaisCategory"));

        tblChoos.setItems(observableList);
    }

    public void on_mouse_clicked(MouseEvent mouseEvent) {
        ExsaisTm dto = tblChoos.getSelectionModel().getSelectedItem();
        if (dto != null) {
            Button btnRemove = new Button("Remove");
            SelectExsaisTm tm = new SelectExsaisTm(dto.getExsaisName(), dto.getExsaisCategory(), "", btnRemove);

            btnRemove.setOnAction(e -> {
                scheduleList.remove(tm);
                tblSchedule1.refresh();
            });

            scheduleList.add(tm);
            tblSchedule1.setItems(scheduleList);
        }
    }

    public void onSendEmail(ActionEvent actionEvent) {
        String customerEmail = txtEmail.getText();
        String cname = txtCustomerName.getText();
        String subject = cname + ", this is your exercise schedule";

        if (customerEmail.isEmpty() || cname.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer name and email are required!").show();
            return;
        }

        if (scheduleList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add at least one exercise to the schedule.").show();
            return;
        }


        StringBuilder tableBuilder = new StringBuilder();
        for (SelectExsaisTm item : scheduleList) {
            tableBuilder.append("<tr>")
                    .append("<td style='border: 1px solid #dddddd; padding: 10px;'>").append(item.getExsaisName()).append("</td>")
                    .append("<td style='border: 1px solid #dddddd; padding: 10px;'>").append(item.getExsaisCategory()).append("</td>")
                    .append("<td style='border: 1px solid #dddddd; padding: 10px;'>").append(item.getReps()).append("</td>")
                    .append("</tr>");
        }

        //  HTML walin karapu email body eka
        String messageBody =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; font-size: 14px; color: #f7f1e3;'>" +
                        "<p>Dear " + cname + ",</p>" +
                        "<p>Your updated gym exercise schedule is ready. This plan has been customized to help you reach your fitness goals efficiently and safely.</p>" +
                        "<h2 style='color: #1e3799;'>✅  Schedule Overview</h2>" +
                        "<table style='border-collapse: collapse; width: 100%; font-size: 16px;'>" +
                        "<thead>" +
                        "<tr style='background-color: #f2f2f2;'>" +
                        "<th style='border: 1px solid #dddddd; padding: 10px;'>Exercise Name</th>" +
                        "<th style='border: 1px solid #dddddd; padding: 10px;'>Category</th>" +
                        "<th style='border: 1px solid #dddddd; padding: 10px;'>Reps</th>" +
                        "</tr>" +
                        "</thead>" +
                        "<tbody>" +
                        tableBuilder.toString() +
                        "</tbody>" +
                        "</table>" +
                        "<p style='margin-top: 20px;'>Stay strong and keep pushing your limits!<br><strong>Your Gym Team</strong></p>" +
                        "</body>" +
                        "</html>";


        String from = "savindunawanjana08@gmail.com";
        String password = "mpho jgxj exan bsue";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(customerEmail));
            message.setSubject(subject);
            message.setContent(messageBody, "text/html; charset=utf-8");

            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email. Please check your connection or credentials.").show();
        }
    }

}
