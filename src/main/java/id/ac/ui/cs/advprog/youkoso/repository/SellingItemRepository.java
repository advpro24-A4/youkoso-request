package id.ac.ui.cs.advprog.youkoso.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE SellingItem s SET s.quantity = ?1 WHERE s.id = ?2")
    void updateQuantity(int quantity, Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM SellingItem s WHERE s.id = ?1")
    void deleteById(Long id);
}