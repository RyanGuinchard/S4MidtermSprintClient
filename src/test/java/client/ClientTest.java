package client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientTest {

    @Mock
    private HttpClient httpClientMock;

    @Mock
    private HttpResponse<String> httpResponseMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Client.setHttpClient(httpClientMock);
    }

    @Test
    public void testFetchAirportsByCityId() throws Exception {
        String jsonResponse = "[{\"name\": \"John F. Kennedy International Airport\"}]";

        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Client.setScanner(new Scanner(System.in));

        Client.fetchAirportsByCityId();

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
        System.setIn(in);
        Client.setScanner(new Scanner(System.in));

        Client.performCustomQuery();

        verify(httpClientMock).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
