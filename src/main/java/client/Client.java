package client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.println("Enter your choice: ");
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
                    System.out.println("Fetching cities...");
                    break;
                case 2:
                    System.out.println("Fetching passengers...");
                    break;
                case 3:
                    System.out.println("Fetching airports...");
                    break;
                case 4:
                    System.out.println("Fetching aircraft...");
                    break;
                case 5:
                    System.out.println("Performing query...");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
            }

        }
    }
    private static void displayMenu() {
        System.out.println("=== Client Application Menu ===");
        System.out.println("1. Fetch Cities");
        System.out.println("2. Fetch Passengers");
        System.out.println("3. Fetch Airports");
        System.out.println("4. Fetch Aircraft");
        System.out.println("5. Perform Query");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}
