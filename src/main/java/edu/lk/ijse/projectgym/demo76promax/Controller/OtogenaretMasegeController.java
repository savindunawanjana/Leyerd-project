package edu.lk.ijse.projectgym.demo76promax.Controller;

import edu.lk.ijse.projectgym.demo76promax.Dtos.CustermerDto;
import edu.lk.ijse.projectgym.demo76promax.Modal.CustormerModel;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class OtogenaretMasegeController {
    private static CustormerModel custormerModel = new CustormerModel();

    public static void sendeMassegeOtomaticaly() throws SQLException, ClassNotFoundException {
        ArrayList<CustermerDto> customers = custormerModel.getAllCustomers();
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        for (CustermerDto customer : customers) {
            String birthDateStr = customer.getCuctermerBirthdayMonth();
            if (birthDateStr == null || birthDateStr.isEmpty()) {
                continue;
            }

            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthDateStr);
            } catch (Exception e) {
                System.out.println("Invalid birthday format for customer: " + customer.getName());
                continue;
            }

            int birthMonth = birthDate.getMonthValue();

            if (birthMonth == currentMonth) {
                String email = customer.getCuctermerEmail();
                String name = customer.getName();

                if (email == null || email.isEmpty() || name == null || name.isEmpty()) {
                    System.out.println("Missing email or name for customer: " + customer);
                    continue;
                }

                sendEmailToCustomer(email, name);
            }
        }
    }

    private static void sendEmailToCustomer(String customerEmail, String customerName) throws SQLException, ClassNotFoundException {
        EditOtoJenaretMassege editOtoJenaretMassege = new EditOtoJenaretMassege();
        String cach = editOtoJenaretMassege.getlable();
        String subject = "🎉 Special Birthday Month Offer Just for You, " + customerName + "!";
        String messageBody =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; font-size: 16px; color: #333;'>" +
                        "<h2 style='color: #1e3799;'>🎉 Happy Birthday Month, " + customerName + "!</h2>" +
                        "<h2 style='color: #1e3799;'>" + cach + "!</h2>" +
                        "<p style='background-color: #f1f2f6; padding: 15px; border-left: 5px solid #1e3799;'>" +
                        "As a token of our appreciation, <strong>you’re eligible for a special discount</strong> this month at <strong>Your Gym</strong>! 🏋️‍♂️" +
                        "</p>" +
                        "<p>Visit our front desk to claim your offer or contact us if you have any questions.</p>" +
                        "<br>" +
                        "<p>Stay healthy and strong!<br><strong>Your Gym Team 💪</strong></p>" +
                        "</body>" +
                        "</html>";

        final String from = "savindunawanjana08@gmail.com";
        final String password = "mpho jgxj exan bsue";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(customerEmail));
            message.setSubject(subject);
            message.setContent(messageBody, "text/html; charset=utf-8");

            Transport.send(message);


            Platform.runLater(() -> {
                new Alert(Alert.AlertType.INFORMATION, "Birthday offer email sent successfully to " + customerName + "!").show();
            });

        } catch (Exception e) {
            e.printStackTrace();


            Platform.runLater(() -> {
                new Alert(Alert.AlertType.ERROR, "Failed to send email to " + customerName + ". Please check your connection or credentials.").show();
            });
        }
    }

}
