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
import java.util.List;
import java.util.Optional;
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
        request2.setId(UUID.fromString("a5c474a3-3817-44ca-b8cf-cdd127f5e771"));
        request2.setQuantity(20);
        request2.setPrice(150.0);
        request2.setProduct("Request 2");

        requests.add(request1);
        requests.add(request2);
    }

    @Test
    void testCreateRequest() {
        Request request = requests.get(0);
        when(requestRepository.save(request)).thenReturn(request);
        Request createdRequest = requestService.createRequest(request);

        assertEquals(request, createdRequest);
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    void testUpdateRequest() {
        Request request = requests.get(1);
        Request updatedRequest = new Request();

        updatedRequest.setQuantity(20);
        updatedRequest.setPrice(30000);

        when(requestRepository.findRequestById(request.getId())).thenReturn(Optional.of(request));
        when(requestRepository.save(request)).thenReturn(updatedRequest);
        Request result = requestService.updateRequest(request.getId(), updatedRequest);

        verify(requestRepository, times(1)).findRequestById(request.getId());
        verify(requestRepository, times(1)).save(request);
        assertEquals(updatedRequest, result);
    }

    @Test
    void testFindAllRequests() {
        when(requestRepository.findAll()).thenReturn(requests);
        List<Request> foundRequests = requestService.findAllRequest();

        assertEquals(requests, foundRequests);
        verify(requestRepository, times(1)).findAll();
    }

    @Test
    void testFindRequestById() {
        Request request = requests.get(0);
        UUID requestId = request.getId();
        when(requestRepository.findRequestById(requestId)).thenReturn(Optional.of(request));

        Request foundRequest = requestService.findRequestById(requestId);

        assertEquals(request, foundRequest);
        verify(requestRepository, times(1)).findRequestById(requestId);
    }

    @Test
    void testDeleteRequest() {
        Request request = requests.get(0);
        UUID requestId = request.getId();
        when(requestRepository.findRequestById(requestId)).thenReturn(Optional.of(request));

        Request deletedRequest = requestService.deleteRequest(requestId);

        assertEquals(request, deletedRequest);
        verify(requestRepository, times(1)).findRequestById(requestId);
        verify(requestRepository, times(1)).delete(request);
    }
}