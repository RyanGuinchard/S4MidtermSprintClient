package client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientTest {

    @Mock
    private HttpClient httpClientMock;

    @Mock
    private HttpResponse<String> httpResponseMock;

    private Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        Client.setHttpClient(httpClientMock); // Assuming you have a setter for the HttpClient
    }

    @Test
    public void testFetchAirportsByCityId() throws Exception {
        String jsonResponse = "[{\"name\":\"John F. Kennedy International Airport\"}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        // Simulate user input
        Client.setScanner(new java.util.Scanner("1\n1\n\n6\n"));

        // Run the client main method
        Client.main(new String[]{});

        // Verify the output
        verify(httpClientMock, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    public void testPerformCustomQuery() throws Exception {
        String jsonResponse = "[{\"id\":1,\"name\":\"New York\",\"state\":\"NY\",\"population\":8419000,\"airports\":[]}]";
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        // Simulate user input
        Client.setScanner(new java.util.Scanner("5\n1\n\n6\n"));

        // Run the client main method
        client.main(new String[]{});

        // Verify the output
        verify(httpClientMock, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
