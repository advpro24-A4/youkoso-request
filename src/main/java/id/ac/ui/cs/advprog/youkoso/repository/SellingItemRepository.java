package id.ac.ui.cs.advprog.youkoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {
}