package controller;

import dao.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Country;
import model.FirstLevelDivision;
import util.SceneChangerUtil;
import util.ValidatorUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for the add customer view.
 */
public class AddCustomerController implements Initializable {
    private ObservableList<Country> countries;
    private ObservableList<FirstLevelDivision> divisions;
    @FXML
    private TextField addressTxt;
    @FXML
    private ComboBox<Country> countryCBox;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private ComboBox<FirstLevelDivision> divisionCBox;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField postalCodeTxt;

    /**
     * Sets items for country and division combo boxes.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDao countryDao = new CountryDaoImpl();
        try {
            countries = (ObservableList<Country>) countryDao.getAll();
            countryCBox.setItems(countries);
            countryCBox.setConverter(new StringConverter<Country>() {
                @Override
                public String toString(Country country) {
                    if (country != null) {
                        return country.getCountry();
                    }
                    return "";
                }

                @Override
                public Country fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FirstLevelDivisionDao divisionDao = new FirstLevelDivisionDaoImpl();
        try {
            divisions = (ObservableList<FirstLevelDivision>) divisionDao.getAll();
            divisionCBox.setConverter(new StringConverter<FirstLevelDivision>() {
                @Override
                public String toString(FirstLevelDivision division) {
                    if (division != null) {
                        return division.getDivision();
                    }
                    return "";
                }

                @Override
                public FirstLevelDivision fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filters division combo box after selecting a country.
     *
     * @param event ActionEvent after selecting a country.
     */
    @FXML
    void onActionSelectCountry(ActionEvent event) {
        int countryId = countryCBox.getValue().getCountryId();
        FilteredList<FirstLevelDivision> filteredDivisions = divisions.filtered(division -> division.getCountryId() == countryId);
        divisionCBox.setItems(filteredDivisions);
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
     * Validates and creates a new customer after save is clicked.
     * Returns to dashboard after successful creation.
     *
     * @param event ActionEvent from clicking save.
     * @throws SQLException from DAO.
     * @throws IOException from FXMLLoader.
     */
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        CustomerDao customerDao = new CustomerDaoImpl();
        String name = customerNameTxt.getText();
        String address = addressTxt.getText();
        String postalCode = postalCodeTxt.getText();
        String phone = phoneTxt.getText();
        Country country = countryCBox.getValue();
        FirstLevelDivision division = divisionCBox.getValue();

        if (ValidatorUtil.validate(name, address, postalCode, phone, country, division)) {
            int divisionId = division.getDivisionId();

            customerDao.create(name, address, postalCode, phone, divisionId);
            SceneChangerUtil.changeScene(event, "/view/dashboard.fxml");
        }
    }
}
