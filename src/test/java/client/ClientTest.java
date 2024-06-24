package client;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        String mockResponseBody = "[{\"name\":\"John F. Kennedy International Airport\"}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(mockResponseBody);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandlers.ofString().getClass())))
                .thenReturn(httpResponseMock);

        InputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Client.fetchAirportsByCityId();

        String expectedOutput = "Airport Name: John F. Kennedy International Airport\nPress Enter to continue...\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPerformCustomQuery() throws Exception {
        String mockResponseBody = "[{\"id\":1,\"name\":\"New York\",\"state\":\"NY\",\"population\":8419000,\"airports\":[]}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(mockResponseBody);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandlers.ofString().getClass())))
                .thenReturn(httpResponseMock);

        InputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Client.performCustomQuery();

        String expectedOutput = "City: New York\nState: NY\nAirports:\n\nPress Enter to continue...\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
