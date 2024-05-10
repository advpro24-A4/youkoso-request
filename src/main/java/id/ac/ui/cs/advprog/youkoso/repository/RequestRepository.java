package id.ac.ui.cs.advprog.youkoso.repository;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class RequestRepository {

    private List<Request> requestData = new ArrayList<>();


    public Request createRequest(Request request) {
        if (request.getId() == null) {
            UUID uuid = UUID.randomUUID();
            request.setId(uuid);
        }

        requestData.add(request);
        return request;
    }

    public Request updateRequest(UUID requestId, Request updatedRequest) {
        for (Request request : requestData) {
            if (request.getId().equals(requestId)) {
                request.setId(updatedRequest.getId());
                request.setProduct(updatedRequest.getProduct());
                request.setQuantity(updatedRequest.getQuantity());
                request.setPrice(updatedRequest.getPrice());
                return request;
            }
        }
        return null;
    }

    public void editRequest(Request request) {
        updateRequest(request.getId(), request);
    }

    public Iterator<Request> findAllRequest() {
        return requestData.iterator();
    }

    public Request findRequestById(UUID requestId) {
        return requestData.stream()
                .filter(request -> request.getId().equals(requestId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid request Id:" + requestId)
                );
    }

    public Request deleteRequest(UUID requestId) {
        Request request = findRequestById(requestId);
        requestData.remove(request);
        return request;
    }
}