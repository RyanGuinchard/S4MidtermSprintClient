package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SimpleClientTest {
    @Mock
    private HttpClient httpClientMock;

    @Mock
    private HttpResponse<String> httpResponseMock;

    @Mock
    private Scanner scannerMock;

    @Mock
    private ObjectMapper objectMapperMock;

    @InjectMocks
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Client.setHttpClient(httpClientMock);
        Client.setScanner(scannerMock);
    }
    @Test
    void testFetchAirportsByPassengerId() throws Exception {
        when(scannerMock.nextInt()).thenReturn(1);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn("[]");

        JsonNode emptyNode = JsonNodeFactory.instance.arrayNode();
        when(objectMapperMock.readTree(any(String.class))).thenReturn(emptyNode);

        Client.fetchAirportsByPassengerId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testFetchAirportsByCityId() throws Exception {
        when(scannerMock.nextInt()).thenReturn(Integer.valueOf(1));
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);
        when(httpResponseMock.statusCode()).thenReturn(Integer.valueOf(200));
        when(httpResponseMock.body()).thenReturn("[]");

        JsonNode emptyNode = JsonNodeFactory.instance.arrayNode();
        when(objectMapperMock.readTree(any(String.class))).thenReturn(emptyNode);

        Client.fetchAirportsByCityId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
    @Test
    void testFetchAirportsByCityIdWithNon200Status() throws Exception {
        when(scannerMock.nextInt()).thenReturn(1);
        when(scannerMock.nextLine()).thenReturn(""); // Consume newline
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);
        when(httpResponseMock.statusCode()).thenReturn(404);
        when(httpResponseMock.body()).thenReturn("Not Found");

        Client.fetchAirportsByCityId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponseMock, times(2)).statusCode(); // Ensure statusCode() is called twice
        verify(httpResponseMock, times(1)).body(); // Ensure body() is called once
    }



    @Test
    void testPerformCustomQuery() throws Exception {
        when(scannerMock.nextInt()).thenReturn(Integer.valueOf(1));
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);
        when(httpResponseMock.statusCode()).thenReturn(Integer.valueOf(200));
        when(httpResponseMock.body()).thenReturn("[]");

        JsonNode emptyNode = JsonNodeFactory.instance.arrayNode();
        when(objectMapperMock.readTree(any(String.class))).thenReturn(emptyNode);

        Client.performCustomQuery();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
    @Test
    void testInvalidMenuChoice() {
        when(scannerMock.hasNextInt()).thenReturn(true, true); // Expecting two integer inputs
        when(scannerMock.nextInt()).thenReturn(999, 6); // First invalid choice, then exit option
        when(scannerMock.nextLine()).thenReturn(""); // Consume newline
        Client.main(new String[]{});
        verify(scannerMock, times(2)).nextInt(); // Ensure it was called twice: once for invalid, once for exit
        verify(scannerMock, atLeast(2)).nextLine(); // Ensure it consumes the newline at least twice
    }


    @Test
    void testExitOption() {
        when(scannerMock.hasNextInt()).thenReturn(true);
        when(scannerMock.nextInt()).thenReturn(6); // Exit option
        when(scannerMock.nextLine()).thenReturn(""); // Consume newline
        Client.main(new String[]{});
        verify(scannerMock).nextInt();
        verify(scannerMock, atLeastOnce()).nextLine(); // Verify nextLine() is called to consume newlines
    }

    @Test
    void testPerformCustomQueryWithInvalidChoice() throws Exception {
        when(scannerMock.nextInt()).thenReturn(999); // Invalid choice
        when(scannerMock.nextLine()).thenReturn(""); // Consume newline

        Client.performCustomQuery();

        verify(httpClientMock, never()).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testFetchAirportsWhereAircraftCanLandAndTakeOff() throws Exception {
        // Arrange
        int aircraftId = 3;  // Example aircraft ID
        when(scannerMock.nextInt()).thenReturn(Integer.valueOf(aircraftId));
        when(scannerMock.nextLine()).thenReturn("");

        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        when(httpResponseMock.statusCode()).thenReturn(Integer.valueOf(200));
        when(httpResponseMock.body()).thenReturn("[]");

        // Act
        Client.fetchAirportsWhereAircraftCanLandAndTakeOff();

        // Assert
        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponseMock).body();  // Ensure the response body is accessed
    }


}
