package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellingItemService {
    SellingItem createSellingItem(SellingItem sellingItem);
    SellingItem updateSellingItem(UUID itemId, SellingItem updatedSellingItem);
    List<SellingItem> findAllSellingItems();
    Optional<SellingItem> findSellingItemById(UUID itemId);
    SellingItem deleteSellingItem(UUID itemId);
}