package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import id.ac.ui.cs.advprog.youkoso.model.Request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
public class RequestRepositoryTest {
    @InjectMocks
    RequestRepository requestRepository;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Request request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();

        requestRepository.createRequest(request);

        Iterator<Request> requestIterator = requestRepository.findAllRequest();
        assertTrue(requestIterator.hasNext());

        Request foundRequest = requestIterator.next();
        assertEquals(request.getId(), foundRequest.getId());
        assertEquals(request.getQuantity(), foundRequest.getQuantity());
        assertEquals(request.getPrice(), foundRequest.getPrice());
        assertEquals(request.getProduct(), foundRequest.getProduct());
        assertEquals(request.getCurrency(), foundRequest.getCurrency());
    }



    @Test
    void testFindAllIfEmpty() {
        Iterator<Request> requests = requestRepository.findAllRequest();
        assertFalse(requests.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneRequest() {
        Request request1 = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();
        requestRepository.createRequest(request1);

        Request request2 = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e732"))
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();
        requestRepository.createRequest(request2);

        Iterator<Request> requestIterator = requestRepository.findAllRequest();
        assertTrue(requestIterator.hasNext());

        Request savedRequest = requestIterator.next();
        assertEquals(request1.getId(), savedRequest.getId());
        savedRequest = requestIterator.next();
        assertEquals(request2.getId(), savedRequest.getId());
    }

    @Test
    void testDeleteRequest() {
        Request request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();

        requestRepository.createRequest(request);

        requestRepository.deleteRequest(request.getId());
        Request searchDeletedRequest = requestRepository.findRequestById(request.getId());
        assertNull(searchDeletedRequest);
    }

    @Test
    void testEditRequest() {
        Request request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();
        requestRepository.createRequest(request);

        Request editedRequest = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(10)
                .requestPrice(200.0)
                .requestProduct("Product Name")
                .requestCurrency("IDR")
                .build();
        requestRepository.editRequest(editedRequest);

        Request foundRequest = requestRepository.findRequestById(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"));
        assertEquals(editedRequest.getId(), foundRequest.getId());
        assertEquals(editedRequest.getQuantity(), foundRequest.getQuantity());
        assertEquals(editedRequest.getPrice(), foundRequest.getPrice());
        assertEquals(editedRequest.getProduct(), foundRequest.getProduct());
        assertEquals(editedRequest.getCurrency(), foundRequest.getCurrency());
    }
}
