package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Client {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
                    fetchAirportsByCityId();
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

            if (!exit) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
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

    private static void fetchAirportsByCityId() {
        System.out.print("Enter City ID: ");
        int cityId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/cities/" + cityId + "/airports"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                prettyPrintAirports(response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void prettyPrintAirports(String responseBody) {
        try {
            JsonNode airportsNode = objectMapper.readTree(responseBody);
            if (airportsNode.isArray()) {
                for (JsonNode airportNode : airportsNode) {
                    String airportName = airportNode.get("name").asText();
                    System.out.println("Airport Name: " + airportName);
                }
            } else {
                System.out.println("No airports found for the specified city.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
