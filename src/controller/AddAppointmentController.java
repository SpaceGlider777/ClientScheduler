package controller;

import dao.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Contact;
import model.CustomerDTO;
import model.User;
import util.SceneChangerUtil;
import util.ValidatorUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Controller for the add appointment view
 */
public class AddAppointmentController implements Initializable {
    private ObservableList<Contact> contacts;
    private ObservableList<CustomerDTO> customers;
    private ObservableList<User> users;
    @FXML
    private ComboBox<Contact> contactCBox;
    @FXML
    private ComboBox<CustomerDTO> customerCBox;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField endHourTxt;
    @FXML
    private TextField endMinTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField startHourTxt;
    @FXML
    private TextField startMinTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField typeTxt;
    @FXML
    private ComboBox<User> userCBox;

    /**
     * Sets items for contact, customer, and user combo boxes.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDao contactDao = new ContactDaoImpl();
        try {
            contacts = (ObservableList<Contact>) contactDao.getAll();
            contactCBox.setItems(contacts);
            contactCBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Contact contact) {
                    if (contact != null) {
                        return contact.getContactName();
                    }
                    return "";
                }

                @Override
                public Contact fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CustomerDao customerDao = new CustomerDaoImpl();
        try {
            customers = (ObservableList<CustomerDTO>) customerDao.getAll();
            customerCBox.setItems(customers);
            customerCBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(CustomerDTO customerDTO) {
                    if (customerDTO != null) {
                        return customerDTO.getCustomerName();
                    }
                    return "";
                }

                @Override
                public CustomerDTO fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDaoImpl();
        try {
            users = (ObservableList<User>) userDao.getAll();
            userCBox.setItems(users);
            userCBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(User user) {
                    if (user != null) {
                        return user.getUserName();
                    }
                    return "";
                }

                @Override
                public User fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns to dashboard after cancel is clicked.
     *
     * @param event ActionEvent from clicking cancel.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/view/dashboard.fxml");
    }

    /**
     * Validates and creates a new appointment after save is clicked.
     * Returns to dashboard after successful creation.
     *
     * @param event ActionEvent from clicking save.
     * @throws SQLException from DAO.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        String title = titleTxt.getText();
        String description = descriptionTxt.getText();
        String location = locationTxt.getText();
        Contact contact = contactCBox.getValue();
        String type = typeTxt.getText();
        LocalDate startDate = startDatePicker.getValue();
        String startHour = startHourTxt.getText();
        String startMin = startMinTxt.getText();
        String endHour = endHourTxt.getText();
        String endMin = endMinTxt.getText();
        CustomerDTO customer = customerCBox.getValue();
        User user = userCBox.getValue();

        if (ValidatorUtil.validate(title, description, location, contact, type, startDate, startHour, startMin, endHour, endMin, customer, user)) {
            LocalTime startTime = LocalTime.of(Integer.parseInt(startHourTxt.getText()), Integer.parseInt(startMinTxt.getText()));
            LocalDateTime start = LocalDateTime.of(startDate, startTime);
            LocalTime endTime = LocalTime.of(Integer.parseInt(endHourTxt.getText()), Integer.parseInt(endMinTxt.getText()));
            LocalDateTime end = LocalDateTime.of(startDate, endTime);
            int contactId = contact.getContactId();
            int customerId = customer.getCustomerId();
            int userId = user.getUserId();

            if (ValidatorUtil.validateTimeOverlap(start, end, customerId)) {
                appointmentDao.create(title, description, location, contactId, type, start, end, customerId, userId);
                SceneChangerUtil.changeScene(event, "/view/dashboard.fxml");
            }
        }
    }
}
