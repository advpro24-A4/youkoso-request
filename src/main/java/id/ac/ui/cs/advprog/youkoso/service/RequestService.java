package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import java.util.List;
public interface RequestService {
    public Request createRequest(Request request);
    public Request updateRequest(String requestId, Request updatedRequest);
    public List <Request> findAllRequest();
    public Request findRequestById(String requestId);
    public Request deleteRequest(String requestId);
}
