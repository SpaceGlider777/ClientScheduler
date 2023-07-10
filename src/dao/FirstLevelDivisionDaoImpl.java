package dao;

import javafx.collections.FXCollections;
import model.FirstLevelDivision;
import util.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation for FirstLevelDivisionDao.
 */
public class FirstLevelDivisionDaoImpl implements FirstLevelDivisionDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<FirstLevelDivision> getAll() throws SQLException {
        List<FirstLevelDivision> divisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            FirstLevelDivision divisionObj = new FirstLevelDivision(divisionId, division, countryId);
            divisions.add(divisionObj);
        }

        return divisions;
    }
}
