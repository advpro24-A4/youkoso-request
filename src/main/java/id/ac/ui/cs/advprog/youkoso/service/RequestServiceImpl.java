package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Request> optionalRequest = requestRepository.findRequestById(requestId);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setQuantity(updatedRequest.getQuantity());
            request.setPrice(updatedRequest.getPrice());
            request.setProduct(updatedRequest.getProduct());
            request.setCurrency(updatedRequest.getCurrency());
            return requestRepository.save(request);
        }
        return null;
    }

    @Override
    public List<Request> findAllRequest() {
        return requestRepository.findAll();
    }

    @Override
    public Request findRequestById(UUID requestId) {
        Optional<Request> optionalRequest = requestRepository.findRequestById(requestId);
        return optionalRequest.orElse(null);
    }

    @Override
    public Request deleteRequest(UUID requestId) {
        Optional<Request> optionalRequest = requestRepository.findRequestById(requestId);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            requestRepository.delete(request);
            return request;
        }
        return null;
    }
}