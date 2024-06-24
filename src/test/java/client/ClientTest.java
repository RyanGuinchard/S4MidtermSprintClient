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
    private HttpResponse httpResponseMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        httpClientMock = mock(HttpClient.class);
        httpResponseMock = mock(HttpResponse.class);
        Client.setHttpClient(httpClientMock);
    }

    @Test
    public void testFetchAirportsByCityId() throws Exception {
        System.out.println("Setting up mock responses for fetchAirportsByCityId test.");
        String jsonResponse = "[{\"name\": \"John F. Kennedy International Airport\"}]";

        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner testScanner = new Scanner(System.in);
        Client.setScanner(testScanner);

        Client.fetchAirportsByCityId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        System.out.println("fetchAirportsByCityId test completed.");

        testScanner.close();
    }

    @Test
    public void testPerformCustomQuery() throws Exception {
        System.out.println("Setting up mock responses for performCustomQuery test.");
        String jsonResponse = "[{\"name\": \"John F. Kennedy International Airport\"}]";

        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner testScanner = new Scanner(System.in);
        Client.setScanner(testScanner);

        Client.performCustomQuery();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        System.out.println("performCustomQuery test completed.");

        testScanner.close();
    }
}
