package edu.lk.ijse.projectgym.demo76promax.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MonthlyMessagesendPageController {

    public Button sendButtenId;
    public Button viewRiportId;

    public void on_action_forButten(ActionEvent actionEvent) {

        sendButtenId.setDisable(true);


        new Thread(() -> {
            try {

                OtogenaretMasegeController.sendeMassegeOtomaticaly();

                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.INFORMATION, "Monthly message sent successfully").show();
                    sendButtenId.setDisable(false);
                });

            } catch (Exception e) {
                e.printStackTrace();


                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.ERROR, "Failed to send message.").show();
                    sendButtenId.setDisable(false);
                });
            }
        }).start();
    }


}
