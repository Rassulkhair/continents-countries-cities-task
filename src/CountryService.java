import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountryService {
    public void createCountry(String countryName, int continentId) {

        String sql = """
                INSERT INTO countries(name, continent_id) VALUES(?, ?)
                """;
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            preparedStatement.setInt(2, continentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
