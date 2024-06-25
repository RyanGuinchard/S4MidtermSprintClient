package client;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleClientTest {

    @Test
    void testDisplayMenu() {
        // Redirect output to a string to capture it
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to test
        Client.displayMenu();

        // Verify the output
        String expectedOutput = "=== Client Application Menu ===\n" +
                "1. What airports are in what cities?\n" +
                "2. List all aircraft passengers have travelled on?\n" +
                "3. Which airports can aircraft take off from and land at?\n" +
                "4. What airports have passengers used?\n" +
                "5. Perform custom query\n" +
                "6. Exit\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}
