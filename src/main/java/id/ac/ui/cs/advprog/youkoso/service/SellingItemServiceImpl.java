package id.ac.ui.cs.advprog.youkoso.service;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import id.ac.ui.cs.advprog.youkoso.repository.SellingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SellingItemServiceImpl implements SellingItemService {

    private final SellingItemRepository sellingItemRepository;

    @Autowired
    public SellingItemServiceImpl(SellingItemRepository sellingItemRepository) {
        this.sellingItemRepository = sellingItemRepository;
    }

    @Override
    public SellingItem createSellingItem(SellingItem sellingItem) {
        return sellingItemRepository.save(sellingItem);
    }

    @Override
    public SellingItem updateSellingItem(int itemId, SellingItem updatedSellingItem) {
        Optional<SellingItem> sellingItemOptional = sellingItemRepository.findById(itemId);
        if (sellingItemOptional.isPresent()) {
            SellingItem sellingItem = sellingItemOptional.get();
            // update the sellingItem fields here
            return sellingItemRepository.save(sellingItem);
        } else {
            return null;
        }
    }

    @Override
    public List<SellingItem> findAllSellingItems() {
        return sellingItemRepository.findAll();
    }

    @Override
    public Optional<SellingItem> findSellingItemById(int itemId) {
        return sellingItemRepository.findById(itemId);
    }

    @Override
    public SellingItem deleteSellingItem(int itemId) {
        Optional<SellingItem> sellingItemOptional = sellingItemRepository.findById(itemId);
        if (sellingItemOptional.isPresent()) {
            SellingItem sellingItem = sellingItemOptional.get();
            sellingItemRepository.delete(sellingItem);
            return sellingItem;
        } else {
            return null;
        }
    }
}