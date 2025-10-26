/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package as20240979;

import java.util.Scanner;

/**
 *
 * @author BEST
 */
public class AS20240979 {

  
    static final int MAX_CITIES = 30;          // Maximum number of cities
    static String[] cities = new String[MAX_CITIES];
    static int cityCount = 5;                  // Start with 5 predefined cities
    static Scanner scanner = new Scanner(System.in);
    static int[][] distance = new int[MAX_CITIES][MAX_CITIES];

    public static void main(String[] args) {
        cities[0] = "Colombo";
        cities[1] = "Kandy";
        cities[2] = "Galle";
        cities[3] = "Jaffna";
        cities[4] = "Matara";

        int choice;
        do {
            System.out.println("\n===== City Management System =====");
            System.out.println("1. Add a new city");
            System.out.println("2. Rename a city");
            System.out.println("3. Remove a city");
            System.out.println("4. Display all cities");
            System.out.println("5. Input or Edit distance between cities");
            System.out.println("6. Display distance table");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addCity();
                case 2 -> renameCity();
                case 3 -> removeCity();
                case 4 -> displayCities();
                case 5 -> editDistance();
                case 6 -> displayDistanceTable();
                case 0 -> System.out.println("Exiting City Management...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

        // Add a new city
   
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

        cities[cityCount++] = newCity;
        System.out.println(newCity + " has been added successfully.");
    }

    
    // Rename a city
   
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
            System.out.println("This city name already exists!");
            return;
        }

        cities[index] = newName;
        System.out.println("City renamed from " + oldName + " to " + newName + ".");
    }

    
    // Remove a city
    
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
            distance[i] = distance[i + 1];
        }
        for (int i = 0; i < cityCount; i++) {
            for (int j = index; j < cityCount - 1; j++) {
                distance[i][j] = distance[i][j + 1];
            }
        }
        
        cityCount--;
        System.out.println(cityToRemove + " has been removed successfully.");
    }

    
    // Display all cities
    
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
    
    // Distance Management
  
    static void editDistance() {
        if (cityCount < 2) {
            System.out.println("At least two cities are required!");
            return;
        }
        
        displayCities();
        System.out.print("Enter the index of the first city: ");
        int i = getIntInput() - 1;

        System.out.print("Enter the index of the second city: ");
        int j = getIntInput() - 1;

        if (i < 0 || j < 0 || i >= cityCount || j >= cityCount) {
            System.out.println("Invalid city index!");
            return;
        }

        if (i == j) {
            System.out.println("Distance from a city to itself is 0.");
            distance[i][j] = 0;
            return;
        }

        System.out.print("Enter the distance between " + cities[i] + " and " + cities[j] + " (in km): ");
        int d = getIntInput();

        if (d < 0) {
            System.out.println("Distance cannot be negative!");
            return;
        }

        distance[i][j] = d;
        distance[j][i] = d; // make symmetrical

        System.out.println("Distance updated successfully between " + cities[i] + " and " + cities[j] + ".");
    }

    static void displayDistanceTable() {
        if (cityCount < 2) {
            System.out.println("Not enough cities to display distances.");
            return;
        }

        System.out.println("\n===== Distance Table (km) =====");

        // Header row
        System.out.printf("%12s", "");
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%12s", cities[i]);
        }
        System.out.println();

        // Distance data
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%12s", cities[i]);
            for (int j = 0; j < cityCount; j++) {
                System.out.printf("%12d", distance[i][j]);
            }
            System.out.println();
        }
    }

    
    // Find city index (Helper method)
   
    static int findCityIndex(String cityName) {
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equalsIgnoreCase(cityName)) {
                return i;
            }
        }
        return -1;
    }

   
    static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
  
