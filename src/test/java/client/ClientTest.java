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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientTest {

    @Mock
    private HttpClient httpClientMock;

    @Mock
    private HttpResponse<String> httpResponseMock;

    @InjectMocks
    private Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        Client.setHttpClient(httpClientMock); // Set the mock HttpClient
    }

    @Test
    public void testFetchAirportsByCityId() throws Exception {
        String jsonResponse = "[{\"name\": \"John F. Kennedy International Airport\"}]";

        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        Client.setScanner(scanner); // Set the mock Scanner

        client.fetchAirportsByCityId();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    public void testPerformCustomQuery() throws Exception {
        String jsonResponse = "[{\"name\": \"John F. Kennedy International Airport\"}]";

        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        Client.setScanner(scanner); // Set the mock Scanner

        client.performCustomQuery();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
