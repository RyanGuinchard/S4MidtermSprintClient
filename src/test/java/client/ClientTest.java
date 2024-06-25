package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    public void testFetchAirportsByCityId() throws IOException, InterruptedException {
        // Arrange
        String mockResponseBody = "[{\"name\":\"John F. Kennedy International Airport\"}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(mockResponseBody);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        // Set scanner input
        Client.setScanner(new Scanner(new ByteArrayInputStream("1\n".getBytes())));

        // Act
        Client.fetchAirportsByCityId();

        // Assert
        assertEquals(200, httpResponseMock.statusCode());
    }

    @Test
    public void testPerformCustomQuery() throws IOException, InterruptedException {
        // Arrange
        String mockResponseBody = "[{\"name\":\"City Name\",\"state\":\"State Name\"}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(mockResponseBody);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        // Set scanner input
        Client.setScanner(new Scanner(new ByteArrayInputStream("1\n".getBytes())));

        // Act
        Client.performCustomQuery();

        // Assert
        assertEquals(200, httpResponseMock.statusCode());
    }
}
