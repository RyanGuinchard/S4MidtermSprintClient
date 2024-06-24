package client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ClientTest {

    private HttpClient httpClientMock;
    private HttpResponse<String> httpResponseMock;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Initializing mocks");
        MockitoAnnotations.openMocks(this);
        httpClientMock = mock(HttpClient.class);
        httpResponseMock = mock(HttpResponse.class);
        Client.setHttpClient(httpClientMock);

        // Debug prints
        System.out.println("httpClientMock initialized: " + (httpClientMock != null));
        System.out.println("httpResponseMock initialized: " + (httpResponseMock != null));

        // Set up mock responses here to ensure they are initialized correctly
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn("[{\"name\": \"John F. Kennedy International Airport\"}]");
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        // Debug prints
        System.out.println("Mock responses set up");
    }

    @Test
    public void testFetchAirportsByCityId() throws Exception {
        System.out.println("Starting testFetchAirportsByCityId");

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Ensure scanner is reset
        Scanner testScanner = new Scanner(System.in);
        Client.setScanner(testScanner);

        Client.fetchAirportsByCityId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        System.out.println("fetchAirportsByCityId test completed");
    }

    @Test
    public void testPerformCustomQuery() throws Exception {
        System.out.println("Starting testPerformCustomQuery");

        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Ensure scanner is reset
        Scanner testScanner = new Scanner(System.in);
        Client.setScanner(testScanner);

        Client.performCustomQuery();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        System.out.println("performCustomQuery test completed");
    }
}
