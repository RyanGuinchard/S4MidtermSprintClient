package client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Fetching cities and their airports...");
                    // Make API call to fetch cities and their airports
                    break;
                case 2:
                    System.out.println("Fetching all aircraft passengers have travelled on...");
                    // Make API call to fetch all aircraft passengers have travelled on
                    break;
                case 3:
                    System.out.println("Fetching airports where aircraft can take off and land...");
                    // Make API call to fetch airports where aircraft can take off and land
                    break;
                case 4:
                    System.out.println("Fetching airports passengers have used...");
                    // Make API call to fetch airports passengers have used
                    break;
                case 5:
                    System.out.println("Performing custom query...");
                    // Implement your custom query functionality
                    break;
                case 6:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("=== Client Application Menu ===");
        System.out.println("1. What airports are in what cities?");
        System.out.println("2. List all aircraft passengers have travelled on?");
        System.out.println("3. Which airports can aircraft take off from and land at?");
        System.out.println("4. What airports have passengers used?");
        System.out.println("5. Perform custom query");
        System.out.println("6. Exit");
    }
}
