package id.ac.ui.cs.advprog.youkoso.repository;

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
        Request request = new Request();
        request.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        request.setQuantity(5);
        request.setPrice(100.0);
        request.setProduct("Product Name");

        requestRepository.create(request);

        List<Request> requests = requestRepository.findAll();
        assertFalse(requests.isEmpty());


        Request foundRequest = requests.get(0);
        assertEquals(request.getId(), foundRequest.getId());
        assertEquals(request.getQuantity(), foundRequest.getQuantity());
        assertEquals(request.getPrice(), foundRequest.getPrice());
        assertEquals(request.getProduct(), foundRequest.getProduct());
    }

    @Test
    void testFindUnavailableRequest() {
        Request request1 = new Request();
        request1.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        request1.setQuantity(5);
        request1.setPrice(100.0);
        request1.setProduct("Product Name");
        requestRepository.create(request1);

        Request request2 = new Request();
        request2.setId("a5c376a3-4817-44da-b8cf-cdd117f5e732");
        request2.setQuantity(5);
        request2.setPrice(100.0);
        request2.setProduct("Product Name");
        requestRepository.create(request2);

        Request obtainedRequest = requestRepository.findById("a5c376a3-4817-44da-b8cf-cdd117f5e733");
        assertNull(obtainedRequest);

    }

    @Test
    void testFindAllIfEmpty() {
        List<Request> requests = requestRepository.findAll();
        assertTrue(requests.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneRequest () {
        Request request1 = new Request();
        request1.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        request1.setQuantity(5);
        request1.setPrice(100.0);
        request1.setProduct("Product Name");
        requestRepository.create(request1);

        Request request2 = new Request();
        request2.setId("a5c376a3-4817-44da-b8cf-cdd117f5e732");
        request2.setQuantity(5);
        request2.setPrice(100.0);
        request2.setProduct("Product Name");
        requestRepository.create(request2);

        List<Request> requests = requestRepository.findAll();
        Iterator<Request> requestIterator = requests.iterator();
        assertTrue(requestIterator.hasNext());

        Request savedRequest = requestIterator.next();
        assertEquals(request1.getId(), savedRequest.getId());
        savedRequest = requestIterator.next();
        assertEquals(request2.getId(), savedRequest.getId());

    }

    @Test
    void testDeleteRequest () {
        Request request = new Request();
        request.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        request.setQuantity(5);
        request.setPrice(100.0);
        request.setProduct("Product Name");
        requestRepository.create(request);

        requestRepository.delete(request.getId());
        List<Request> searchDeletedRequest = requestRepository.findbyId(request.getId());
        assertNull(searchDeletedRequest);
    }

    @Test
    void testEditRequest() {
        Request request = new Request();
        request.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        request.setQuantity(5);
        request.setPrice(100.0);
        request.setProduct("Product Name");
        requestRepository.create(request);

        Request editedRequest = new Request();
        editedRequest.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        editedRequest.setQuantity(10);
        editedRequest.setPrice(200.0);
        editedRequest.setProduct("Product Name");
        requestRepository.edit(editedRequest);

        Request foundRequest = requestRepository.findById("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        assertEquals(editedRequest.getId(), foundRequest.getId());
        assertEquals(editedRequest.getQuantity(), foundRequest.getQuantity());
        assertEquals(editedRequest.getPrice(), foundRequest.getPrice());
        assertEquals(editedRequest.getProduct(), foundRequest.getProduct());
    }
}
