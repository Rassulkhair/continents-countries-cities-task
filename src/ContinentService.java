import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContinentService {
    static List<Continent> getContinents() {
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM continents");
            ResultSet resultSet = ps.executeQuery();
            List<Continent> continentList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Continent continent = new Continent(id, name);
                continentList.add(continent);
            }
            return continentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Continent getContinentById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id");
        int idOfContinent = scanner.nextInt();
        String sql = """
                SELECT * FROM continents WHERE id = ?
                """;
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idOfContinent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new Continent(id, name);
            } else {
                System.out.println("NOT FOUNT");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createContinent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название нового континента");
        String nameOfNewContinent = scanner.next();
        String sql = """
                INSERT INTO continents(name) VALUES (?)
                """;
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameOfNewContinent);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void deleteContinentById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id континента");
        int idOfContinent = scanner.nextInt();
        String sql = """
                DELETE FROM continents WHERE id = ?
                """;
        try {
            Connection connection = ConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOfContinent);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
