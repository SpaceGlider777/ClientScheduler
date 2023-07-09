package dao;

import javafx.collections.FXCollections;
import model.Country;
import util.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implmentation of CountryDao.
 */
public class CountryDaoImpl implements CountryDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() throws SQLException {
        List<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Country countryObj = new Country(countryId, country);
            countries.add(countryObj);
        }

        return countries;
    }
}
