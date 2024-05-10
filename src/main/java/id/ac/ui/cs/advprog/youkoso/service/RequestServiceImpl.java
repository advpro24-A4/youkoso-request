package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request createRequest(Request request) {
        requestRepository.createRequest(request);
        return request;
    }

    @Override
    public Request updateRequest(UUID requestId, Request updatedRequest) {
        requestRepository.updateRequest(requestId, updatedRequest);
        return updatedRequest;
    }

    @Override
    public List<Request> findAllRequest() {
        List<Request> requests = new ArrayList<>();
        Iterator<Request> requestIterator = requestRepository.findAllRequest();
        requestIterator.forEachRemaining(requests::add);
        return requests;
    }

    @Override
    public Request findRequestById(UUID requestId) {
        return requestRepository.findRequestById(requestId);
    }

    @Override
    public Request deleteRequest(UUID requestId) {
        return requestRepository.deleteRequest(requestId);
    }
}