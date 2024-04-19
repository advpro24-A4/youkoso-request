package id.ac.ui.cs.advprog.youkoso.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;

@Repository
public class RequestRepository {
    public Request create(Request request) {
        return null;
    }

    public void delete(Request request) {
        return;
    }

    public List<Request> findAll() {
        return null;
    }

    public Request findRequestById(String id) {
        return null;
    }

    public void editRequest(String id, int quantity, double price, String product) {
        return;
    }

}