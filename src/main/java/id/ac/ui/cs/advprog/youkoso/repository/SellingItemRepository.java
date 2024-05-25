package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.youkoso.model.Request;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SellingItemRepository extends JpaRepository<SellingItem, Integer> {

    Optional<SellingItem> findSellingItemById(int itemId);

    Optional<SellingItem> findByQuantity(int quantity);

    Optional<SellingItem> findByRequest(Request request);
}