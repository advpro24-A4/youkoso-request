package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;

import java.util.List;
import java.util.UUID;

public interface RequestService {
    Request createRequest(Request request);
    Request updateRequest(UUID requestId, Request updatedRequest);
    List<Request> findAllRequest();
    Request findRequestById(UUID requestId);
    Request deleteRequest(UUID requestId);
}