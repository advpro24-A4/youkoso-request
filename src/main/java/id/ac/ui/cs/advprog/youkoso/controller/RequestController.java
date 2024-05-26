package id.ac.ui.cs.advprog.youkoso.controller;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @PostMapping("/create")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        if (createdRequest != null) {
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.findAllRequest();
        if (!requests.isEmpty()) {
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable UUID requestId) {
        Request request = requestService.findRequestById(requestId);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{requestId}")
    public ResponseEntity<Request> updateRequest(@PathVariable UUID requestId, @RequestBody Request updatedRequest) {
        Request request = requestService.updateRequest(requestId, updatedRequest);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{requestId}")
    public ResponseEntity<Request> deleteRequest(@PathVariable UUID requestId) {
        Request request = requestService.deleteRequest(requestId);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
