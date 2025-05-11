import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CityService {
    public static void getCitiesByCountries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите названия стран (через запятую и пробел): ");
        String input = scanner.nextLine();

        String[] countryNames = input.split(",\\s*");

        String placeholders = String.join(",", java.util.Collections.nCopies(countryNames.length, "?"));

        String sql = "SELECT cities.id, cities.name, cities.population " +
                "FROM cities " +
                "JOIN countries ON countries.id = cities.country_id " +
                "WHERE countries.name IN (" + placeholders + ")";
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < countryNames.length; i++) {
                preparedStatement.setString(i + 1, countryNames[i]);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOfCountry = rs.getInt("id");
                String cityName = rs.getString("name");
                int population = rs.getInt("population");
                System.out.printf("%d. %s [%d]\n", idOfCountry, cityName, population);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
