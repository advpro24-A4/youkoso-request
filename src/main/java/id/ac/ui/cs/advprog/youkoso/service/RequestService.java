package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import java.util.List;
public interface RequestService {
    Request createRequest(Request request);
    Request updateRequest(String requestId, Request updatedRequest);
    List <Request> findAllRequest();
    Request findRequestById(String requestId);
    Request deleteRequest(String requestId);
}
