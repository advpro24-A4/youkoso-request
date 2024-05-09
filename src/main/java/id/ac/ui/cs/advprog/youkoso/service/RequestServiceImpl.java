package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request createRequest(Request request) {
        return null;
    }

    @Override
    public Request updateRequest(String requestId, Request updatedRequest) {
        return null;
    }

    @Override
    public List<Request> findAllRequest() {
        return null;
    }

    @Override
    public Request findRequestById(String requestId) {
        return null;
    }

    @Override
    public Request deleteRequest(String requestId) {
        return null;
    }

}
