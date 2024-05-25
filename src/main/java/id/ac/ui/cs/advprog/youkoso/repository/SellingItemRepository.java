package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.youkoso.model.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {

    Optional<SellingItem> findSellingItemById(UUID itemId);

    Optional<SellingItem> findByQuantity(int quantity);

    Optional<SellingItem> findByRequest(Request request);


}