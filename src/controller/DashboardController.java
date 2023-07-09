package controller;

import dao.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.*;
import util.CurrentUser;
import util.NotificationUtil;
import util.SceneChangerUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Controller for the dashboard view.
 * Displays tables for customers and appointments with CRUD functionality.
 * Displays reports for appointment counts by month and type, contact schedules, and customers by country and division.
 */
public class DashboardController implements Initializable {
    private ObservableList<CustomerDTO> customers;
    @FXML
    private TableView<CustomerDTO> customersTableView;
    @FXML
    private TableColumn<CustomerDTO, String> addressCol;
    @FXML
    private TableColumn<CustomerDTO, Integer> customerIdCol;
    @FXML
    private TableColumn<CustomerDTO, String> customerNameCol;
    @FXML
    private TableColumn<CustomerDTO, Integer> divisionCol;
    @FXML
    private TableColumn<CustomerDTO, String> phoneCol;
    @FXML
    private TableColumn<CustomerDTO, String> postalCodeCol;
    @FXML
    private TableColumn<CustomerDTO, String> countryCol;

    private ObservableList<AppointmentDTO> appointments;
    @FXML
    private TableView<AppointmentDTO> appointmentsTableView;
    @FXML
    private TableColumn<AppointmentDTO, Integer> appointmentIdCol;
    @FXML
    private TableColumn<AppointmentDTO, String> titleCol;
    @FXML
    private TableColumn<AppointmentDTO, String> descriptionCol;
    @FXML
    private TableColumn<AppointmentDTO, String> locationCol;
    @FXML
    private TableColumn<AppointmentDTO, String> contactCol;
    @FXML
    private TableColumn<AppointmentDTO, String> typeCol;
    @FXML
    private TableColumn<AppointmentDTO, LocalDateTime> startCol;
    @FXML
    private TableColumn<AppointmentDTO, LocalDateTime> endCol;
    @FXML
    private TableColumn<AppointmentDTO, Integer> customerIdCol2;
    @FXML
    private TableColumn<AppointmentDTO, Integer> userIdCol;

    private ObservableList<AppointmentCountDTO> appointmentCounts;
    @FXML
    private TableView<AppointmentCountDTO> appointmentCountsTableView;
    @FXML
    private TableColumn<AppointmentCountDTO, String> acMonthCol;
    @FXML
    private TableColumn<AppointmentCountDTO, String> acTypeCol;
    @FXML
    private TableColumn<AppointmentCountDTO, Integer> acCountCol;

    private ObservableList<Contact> contacts;
    @FXML
    private ComboBox<Contact> contactCBox;
    @FXML
    private TableView<AppointmentDTO> contactSchedulesTableView;
    @FXML
    private TableColumn<AppointmentDTO, Integer> csIdCol;
    @FXML
    private TableColumn<AppointmentDTO, String> csTitleCol;
    @FXML
    private TableColumn<AppointmentDTO, String> csTypeCol;
    @FXML
    private TableColumn<AppointmentDTO, String> csDescriptionCol;
    @FXML
    private TableColumn<AppointmentDTO, LocalDateTime> csStartCol;
    @FXML
    private TableColumn<AppointmentDTO, LocalDateTime> csEndCol;
    @FXML
    private TableColumn<AppointmentDTO, Integer> csCustomerIdCol;

    private ObservableList<CustomerCountDTO> customerCounts;
    @FXML
    private TableView<CustomerCountDTO> customerCountsTableView;
    @FXML
    private TableColumn<CustomerCountDTO, String> ccCountryCol;
    @FXML
    private TableColumn<CustomerCountDTO, String> ccDivisionCol;
    @FXML
    private TableColumn<CustomerCountDTO, Integer> ccCountCol;

    /**
     * Sets items for customer, appointment, and reports TableViews.
     * Sets items for contact combo box for the second report.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDao customerDao = new CustomerDaoImpl();
        try {
            customers = (ObservableList<CustomerDTO>) customerDao.getAllWithDivisionData();
            customersTableView.setItems(customers);
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        try {
            appointments = (ObservableList<AppointmentDTO>) appointmentDao.getAll();
            appointmentsTableView.setItems(appointments);
            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdCol2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            appointmentCounts = (ObservableList<AppointmentCountDTO>) appointmentDao.getAllByTypeAndMonth();
            appointmentCountsTableView.setItems(appointmentCounts);
            acMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            acTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            acCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        csIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        csTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        csTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        csDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        csStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        csEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        csCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        try {
            customerCounts = (ObservableList<CustomerCountDTO>) customerDao.getAllByCountryAndDivision();
            customerCountsTableView.setItems(customerCounts);
            ccCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
            ccDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
            ccCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes scene to the add customer view.
     *
     * @param event ActionEvent from clicking add customer.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/view/add-customer.fxml");
    }

    /**
     * Changes scene to the update customer view.
     * Does nothing if no customer is selected.
     *
     * @param event ActionEvent from clicking update customer.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        if (customersTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/update-customer.fxml"));
        loader.load();
        UpdateCustomerController updateCustomerController = loader.getController();
        updateCustomerController.setFields(customersTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes a customer if there are no associated appointments.
     * Does nothing if no customer is selected.
     *
     * @param event ActionEvent from clicking delete customer.
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        if (customersTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        if (NotificationUtil.displayConfirmationAlert("Delete Customer", "Are you sure you want to delete this customer?")) {
            CustomerDTO customer = customersTableView.getSelectionModel().getSelectedItem();
            CustomerDao customerDao = new CustomerDaoImpl();
            try {
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                if (appointmentDao.getCountByCustomerId(customer.getCustomerId()) == 0) {
                    customerDao.delete(customer.getCustomerId());
                    customers.remove(customer);
                    NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Delete Customer", "Customer removed.");
                } else {
                    NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Delete Customer", "Please delete customer's appointments first.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Changes scene to the add appointment view.
     *
     * @param event ActionEvent from clicking add appointment.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/view/add-appointment.fxml");
    }

    /**
     * Changes scene to the update appointment view.
     * Does nothing if no appointment is selected.
     *
     * @param event ActionEvent from clicking update appointment.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {
        if (appointmentsTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/update-appointment.fxml"));
        loader.load();
        UpdateAppointmentController updateAppointmentController = loader.getController();
        updateAppointmentController.setFields(appointmentsTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes an appointment.
     * Does nothing if no appointment is selected.
     *
     * @param event ActionEvent from clicking delete appointment.
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        if (appointmentsTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        if (NotificationUtil.displayConfirmationAlert("Delete Appointment", "Are you sure you want to delete this appointment?")) {
            AppointmentDTO appointment = appointmentsTableView.getSelectionModel().getSelectedItem();
            AppointmentDao appointmentDao = new AppointmentDaoImpl();
            try {
                appointmentDao.delete(appointment.getAppointmentId());
                String message = appointment.getType() + " appointment " + appointment.getAppointmentId() + " has been cancelled.";
                NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Delete Appointment", message);
                appointments.remove(appointment);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets items for appointment TableView to all appointments.
     *
     * @param event ActionEvent from clicking all.
     */
    @FXML
    void onActionAll(ActionEvent event) {
        appointmentsTableView.setItems(appointments);
    }

    /**
     * Sets items for appointment TableView to appointments in the current month.
     * Lambda is used here to filter appointments by the current month.
     *
     * @param event ActionEvent from clicking month.
     */
    @FXML
    void onActionMonth(ActionEvent event) {
        LocalDateTime today = LocalDateTime.now();
        Predicate<AppointmentDTO> isInMonth = appointment -> appointment.getStart().getMonth() == today.getMonth() && appointment.getStart().getYear() == today.getYear();
        appointmentsTableView.setItems(appointments.filtered(isInMonth));
    }

    /**
     * Sets items for appointment TableView to appointments in the current week.
     * Lambda is used here to filter appointments by the current week.
     *
     * @param event ActionEvent from clicking week.
     */
    @FXML
    void onActionWeek(ActionEvent event) {
        TemporalField temporalField = WeekFields.of(Locale.getDefault()).dayOfWeek();
        LocalDate startOfWeek = LocalDate.now().with(temporalField, 1L);
        LocalDate endOfWeek = LocalDate.now().with(temporalField, 7L);
        Predicate<AppointmentDTO> isInWeek = appointment -> !appointment.getStart().toLocalDate().isBefore(startOfWeek) && !appointment.getEnd().toLocalDate().isAfter(endOfWeek);
        appointmentsTableView.setItems(appointments.filtered(isInWeek));
    }

    /**
     * Sets items for contact schedules TableView (2nd report) to appointments for a given contact.
     * Lambda is used here to filter appointments by contact ID.
     *
     * @param event ActionEvent from selecting a contact.
     */
    @FXML
    void onActionSelectContact(ActionEvent event) {
        int contactId = contactCBox.getValue().getContactId();
        Predicate<AppointmentDTO> hasContactId = appointment -> appointment.getContactId() == contactId;
        contactSchedulesTableView.setItems(appointments.filtered(hasContactId));
    }

    /**
     * Signs out and returns to login screen.
     *
     * @param event ActionEvent from clicking sign out.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionSignOut(ActionEvent event) throws IOException {
        CurrentUser.LoggedInUser = null;
        SceneChangerUtil.changeScene(event, "/view/login.fxml");
    }
}
