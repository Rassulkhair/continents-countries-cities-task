import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Вывести список континентов");
            System.out.println("2. Создать континент");
            System.out.println("3. Удалить континент");
            System.out.println("4. Получить города по странам");
            System.out.println("5. Обновить город");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    List<Continent> continents = ContinentService.getContinents();
                continents.forEach(System.out::println);
                }
                case 2 -> ContinentService.createContinent();
                case 3 -> ContinentService.deleteContinentById();
                case 4 -> CityService.getCitiesByCountries();
                case 5 -> CityService.updateCity();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}
