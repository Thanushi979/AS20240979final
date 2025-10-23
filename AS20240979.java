import java.util.Scanner;

/**
 *
 * @author BEST
 */
public class AS20240979 {

    static final int MAX_CITIES = 30;     // Maximum number of cities
    static String[] cities = new String[MAX_CITIES];
    static int cityCount = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       int choice;

        do {
            System.out.println("\n===== City Management System =====");
            System.out.println("1. Add a new city");
            System.out.println("2. Rename a city");
            System.out.println("3. Remove a city");
            System.out.println("4. Display all cities");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = getIntInput();

            switch (choice) {
                case 1 -> addCity();
                case 2 -> renameCity();
                case 3 -> removeCity();
                case 4 -> displayCities();
                case 0 -> System.out.println("Exiting City Management...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // -------------------------------
    // Add a new city
    // -------------------------------
    static void addCity() {
        if (cityCount >= MAX_CITIES) {
            System.out.println("City limit reached! Cannot add more cities.");
            return;
        }

        System.out.print("Enter new city name: ");
        String newCity = scanner.nextLine().trim();

        if (newCity.isEmpty()) {
            System.out.println("City name cannot be empty!");
            return;
        }

        if (findCityIndex(newCity) != -1) {
            System.out.println("This city already exists!");
            return;
        }

        cities[cityCount] = newCity;
        cityCount++;
        System.out.println(newCity + " has been added successfully.");
    }

    // -------------------------------
    // Rename a city
    // -------------------------------
    static void renameCity() {
        if (cityCount == 0) {
            System.out.println("No cities to rename.");
            return;
        }

        displayCities();
        System.out.print("Enter the current city name: ");
        String oldName = scanner.nextLine().trim();
        int index = findCityIndex(oldName);

        if (index == -1) {
            System.out.println("City not found!");
            return;
        }

        System.out.print("Enter the new city name: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("City name cannot be empty!");
            return;
        }

        if (findCityIndex(newName) != -1) {
            System.out.println("This new city name already exists!");
            return;
        }

        cities[index] = newName;
        System.out.println("City renamed from " + oldName + " to " + newName + ".");
    }

    // -------------------------------
    // Remove a city
    // -------------------------------
    static void removeCity() {
        if (cityCount == 0) {
            System.out.println("No cities to remove.");
            return;
        }

        displayCities();
        System.out.print("Enter the city name to remove: ");
        String cityToRemove = scanner.nextLine().trim();
        int index = findCityIndex(cityToRemove);

        if (index == -1) {
            System.out.println("City not found!");
            return;
        }

        for (int i = index; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }

        cityCount--;
        System.out.println(cityToRemove + " has been removed successfully.");
    }

    // -------------------------------
    // Display all cities
    // -------------------------------
    static void displayCities() {
        if (cityCount == 0) {
            System.out.println("No cities have been added yet.");
            return;
        }

        System.out.println("\nList of Cities:");
        for (int i = 0; i < cityCount; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
    }

    // -------------------------------
    // Find city index (Helper method)
    // -------------------------------
    static int findCityIndex(String cityName) {
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equalsIgnoreCase(cityName)) {
                return i;
            }
        }
        return -1;
    }

    // -------------------------------
    // Safe integer input
    // -------------------------------
    static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}