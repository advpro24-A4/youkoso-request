package id.ac.ui.cs.advprog.youkoso.service;


import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.repository.RequestRepository;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import id.ac.ui.cs.advprog.youkoso.repository.SellingItemRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class SellingItemServiceTest {
    @InjectMocks
    private SellingItemServiceImpl sellingItemService;

    @Mock
    private SellingItemRepository sellingItemRepository;

    @Mock
    private RequestRepository requestRepository;

    private SellingItem sellingItem;
    private Request request;

    @BeforeEach
    void setUp() {
        request = new Request();
        request.setId(UUID.randomUUID());
        request.setQuantity(10);
        request.setPrice(10000);

        sellingItem = new SellingItem();
        sellingItem.setId(UUID.randomUUID());
        sellingItem.setQuantity(5);
        sellingItem.setRequest(request);
    }

    @Test
    void testCreateSellingItem() {
        when(sellingItemRepository.save(sellingItem)).thenReturn(sellingItem);
        SellingItem createdSellingItem = sellingItemService.createSellingItem(sellingItem);

        assertEquals(sellingItem, createdSellingItem);
        verify(sellingItemRepository, times(1)).save(sellingItem);
    }

    @Test
    void testUpdateSellingItem() {
        SellingItem updatedSellingItem = new SellingItem();
        updatedSellingItem.setId(sellingItem.getId());
        updatedSellingItem.setQuantity(10);
        updatedSellingItem.setRequest(request);

        when(sellingItemRepository.findSellingItemById(sellingItem.getId())).thenReturn(Optional.of(sellingItem));
        when(sellingItemRepository.save(sellingItem)).thenReturn(updatedSellingItem);
        SellingItem result = sellingItemService.updateSellingItem(sellingItem.getId(), updatedSellingItem);

        verify(sellingItemRepository, times(1)).findSellingItemById(sellingItem.getId());
        verify(sellingItemRepository, times(1)).save(sellingItem);
        assertEquals(updatedSellingItem, result);
    }

    @Test
    void testUpdateSellingItemFailure() {
        UUID itemId = UUID.randomUUID();
        SellingItem updatedSellingItem = new SellingItem();
        updatedSellingItem.setId(itemId);
        updatedSellingItem.setQuantity(10);
        updatedSellingItem.setRequest(request);

        when(sellingItemRepository.findSellingItemById(itemId)).thenReturn(Optional.empty());
        SellingItem result = sellingItemService.updateSellingItem(itemId, updatedSellingItem);

        assertNull(result);
        verify(sellingItemRepository, times(1)).findSellingItemById(itemId);
    }

    @Test
    void testFindAllSellingItems() {
        List<SellingItem> sellingItems = new ArrayList<>();
        sellingItems.add(sellingItem);

        when(sellingItemRepository.findAll()).thenReturn(sellingItems);
        List<SellingItem> foundSellingItems = sellingItemService.findAllSellingItems();

        assertEquals(sellingItems, foundSellingItems);
        verify(sellingItemRepository, times(1)).findAll();
    }

    @Test
    void testFindSellingItemById() {
        when(sellingItemRepository.findSellingItemById(sellingItem.getId())).thenReturn(Optional.of(sellingItem));

        SellingItem foundSellingItem = sellingItemService.findSellingItemById(sellingItem.getId()).orElse(null);

        assertEquals(sellingItem, foundSellingItem);
        verify(sellingItemRepository, times(1)).findSellingItemById(sellingItem.getId());
    }

    @Test
    void testDeleteSellingItem() {
        when(sellingItemRepository.findSellingItemById(sellingItem.getId())).thenReturn(Optional.of(sellingItem));

        SellingItem deletedSellingItem = sellingItemService.deleteSellingItem(sellingItem.getId());

        assertEquals(sellingItem, deletedSellingItem);
        verify(sellingItemRepository, times(1)).findSellingItemById(sellingItem.getId());
        verify(sellingItemRepository, times(1)).delete(sellingItem);
    }

    @Test
    void testDeleteSellingItemFailure() {
        UUID itemId = UUID.randomUUID();

        when(sellingItemRepository.findSellingItemById(itemId)).thenReturn(Optional.empty());
        SellingItem result = sellingItemService.deleteSellingItem(itemId);

        assertNull(result);
        verify(sellingItemRepository, times(1)).findSellingItemById(itemId);
    }


}
