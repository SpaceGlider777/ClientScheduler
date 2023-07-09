package dao;

import model.Country;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for country database access.
 */
public interface CountryDao {
    /**
     * Gets all countries.
     *
     * @return all countries.
     * @throws SQLException from PreparedStatement.
     */
    List<Country> getAll() throws SQLException;
}
