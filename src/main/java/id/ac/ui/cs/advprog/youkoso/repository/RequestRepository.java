package id.ac.ui.cs.advprog.youkoso.repository;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{


    Optional <Request> findRequestById(UUID requestId);

    Optional<Request> findByPrice(double price);

    Optional<Request> findByProduct(String product);
    Optional<Request> findByCurrency(String currency);


}