package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequest(UUID requestId, Request updatedRequest) {
        Request request = requestRepository.findRequestById(requestId);
        request.setQuantity(updatedRequest.getQuantity());
        request.setPrice(updatedRequest.getPrice());
        request.setProduct(updatedRequest.getProduct());
        request.setCurrency(updatedRequest.getCurrency());
        return requestRepository.save(request);
    }

    @Override
    public List<Request> findAllRequest() {
        List<Request> requests = new ArrayList<>();
        Iterator<Request> iterator = requestRepository.findAll().iterator();
        iterator.forEachRemaining(requests::add);
        return requests;
    }

    @Override
    public Request findRequestById(UUID requestId) {
        return requestRepository.findRequestById(requestId);
    }

    @Override
    public Request deleteRequest(UUID requestId) {
        Request request = requestRepository.findRequestById(requestId);
        requestRepository.delete(request);
        return request;
    }




}