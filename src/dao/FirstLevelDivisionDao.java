package dao;

import model.FirstLevelDivision;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for division database access.
 */
public interface FirstLevelDivisionDao {
    /**
     * Gets all divisions.
     *
     * @return all division.
     * @throws SQLException from PreparedStatement.
     */
    List<FirstLevelDivision> getAll() throws SQLException;

    /**
     * Gets divisions given country ID.
     *
     * @param countryId The country ID.
     * @return divisions for the given country.
     * @throws SQLException from PreparedStatement.
     */
    List<FirstLevelDivision> getByCountryId(int countryId) throws SQLException;
}
