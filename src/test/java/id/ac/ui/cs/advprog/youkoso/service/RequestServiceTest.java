package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestServiceImpl requestService;

    List<Request> requests;

    @BeforeEach
    void setUp() {
        requests = new ArrayList<>();

        Request request1 = new Request();
        request1.setId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"));
        request1.setQuantity(10);
        request1.setPrice(100.0);
        request1.setProduct("Request 1");

        Request request2 = new Request();
        request2.setId(UUID.fromString("a5c474a3-3817-44ca-b8uf-cdd127f5e771"));
        request2.setQuantity(20);
        request2.setPrice(150.0);
        request2.setProduct("Request 2");

    }

    @Test
    void testCreateRequest() {
        Request request = requests.getFirst();
        Request createdRequest = requestService.createRequest(request);

        assertEquals(request, createdRequest);
        verify(requestRepository, times(1)).createRequest(createdRequest);
    }

    @Test
    void testUpdateRequest() {
        Request request = requests.get(1);
        Request updatedRequest = new Request();

        updatedRequest.setQuantity(20);
        updatedRequest.setPrice(30000);


        when(requestRepository.updateRequest(request.getId(), updatedRequest)).thenReturn(updatedRequest);
        Request result = requestService.updateRequest(request.getId(), updatedRequest);

        verify(requestRepository, times(1)).updateRequest(request.getId(), updatedRequest);
        assertEquals(updatedRequest, result);
    }

    @Test
    void testFindAllRequests() {
        Request request = requests.get(0);
        when(requestRepository.createRequest(request)).thenReturn(request);
        requestService.createRequest(request);

        Iterator<Request> requestIterator = requests.iterator();

        when(requestRepository.findAllRequest()).thenReturn(requestIterator);
        List<Request> foundRequests = requestService.findAllRequest();

        assertEquals(request, foundRequests.get(0));
        verify(requestRepository, times(1)).findAllRequest();
    }

    @Test
    void testFindRequestById() {
        Request request = requests.get(0);
        UUID requestId = request.getId();
        when(requestRepository.findRequestById(requestId)).thenReturn(request);

        Request foundRequest = requestService.findRequestById(requestId);

        assertEquals(request, foundRequest);
        verify(requestRepository, times(1)).findRequestById(requestId);
    }

    @Test
    void testDeleteRequest() {
        Request request = new Request();
        UUID requestId = request.getId();
        when(requestRepository.deleteRequest(requestId)).thenReturn(request);

        Request deletedRequest = requestService.deleteRequest(requestId);

        assertEquals(request, deletedRequest);
        verify(requestRepository, times(1)).deleteRequest(requestId);
    }


}
