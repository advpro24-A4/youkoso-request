package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellingItemService {
    SellingItem createSellingItem(SellingItem sellingItem);
    SellingItem updateSellingItem(int itemId, SellingItem updatedSellingItem);
    List<SellingItem> findAllSellingItems();
    Optional<SellingItem> findSellingItemById(int itemId);
    SellingItem deleteSellingItem(int itemId);
}