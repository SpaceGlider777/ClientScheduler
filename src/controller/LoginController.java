package controller;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AppointmentDTO;
import util.CurrentUser;
import util.NotificationUtil;
import util.SceneChangerUtil;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for the login view.
 */
public class LoginController implements Initializable {
    private ResourceBundle rb;
    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Label loginLbl;
    @FXML
    private Label timezoneLbl;
    @FXML
    private Label usernameLbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private Button signInBtn;

    /**
     * Sets text in login view based on locale.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
        loginLbl.setText(rb.getString("Login"));
        timezoneLbl.setText(rb.getString("From") + " " + ZoneId.systemDefault());
        usernameLbl.setText(rb.getString("Username"));
        passwordLbl.setText(rb.getString("Password"));
        signInBtn.setText(rb.getString("SignIn"));
    }

    /**
     * Attempts to sign in with given username and password.
     * Changes scene to dashboard if login is successful.
     * Calls logger function to log attempts to login_activity.txt.
     *
     * @param event ActionEvent from clicking sign in.
     * @throws IOException from FXMLLoader.
     * @throws SQLException from DAO.
     */
    public void onActionSignIn(ActionEvent event) throws IOException, SQLException {
        UserDao userDao = new UserDaoImpl();
        CurrentUser.LoggedInUser = userDao.getUser(userNameTxt.getText(), passwordTxt.getText());

        if (CurrentUser.LoggedInUser != null) {
            logSignInAttempt(true, userNameTxt.getText());
            SceneChangerUtil.changeScene(event, "/view/dashboard.fxml");
            AppointmentDao appointmentDao = new AppointmentDaoImpl();
            List<AppointmentDTO> appointments = appointmentDao.getAllInFifteenMinutes(LocalDateTime.now());

            if (!appointments.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");

                for (AppointmentDTO appointment : appointments) {
                    sb.append("Appointment " + appointment.getAppointmentId() + " scheduled for " + appointment.getStart().format(formatter) + ".\n");
                }

                NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Upcoming Appointments", sb.toString());
            } else {
                NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Upcoming Appointments", "There are no upcoming appointments.");
            }
        } else {
            logSignInAttempt(false, userNameTxt.getText());
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, rb.getString("Login"), rb.getString("ErrorMsg"));
        }
    }

    /**
     * Logs login attempts to login_activity.txt.
     *
     * @param isSuccessful True if the login was successful, false otherwise.
     * @param username The username used to login.
     * @throws IOException from FileWriter.
     */
    public void logSignInAttempt(boolean isSuccessful, String username) throws IOException {
        String filename = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename, true);

        if (isSuccessful) {
            fileWriter.append("User " + username + " successfully logged in at " + Instant.now() + " UTC\n");
        } else {
            fileWriter.append("User " + username + " gave invalid log-in at " + Instant.now() + " UTC\n");
        }

        fileWriter.close();
    }
}
