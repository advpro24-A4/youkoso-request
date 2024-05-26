package id.ac.ui.cs.advprog.youkoso.controller;

import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import id.ac.ui.cs.advprog.youkoso.service.SellingItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sellingItem")
public class SellingItemController {

    private final SellingItemService sellingItemService;

    public SellingItemController(SellingItemService sellingItemService) {
        this.sellingItemService = sellingItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<SellingItem> createSellingItem(@RequestBody SellingItem sellingItem) {
        SellingItem createdSellingItem = sellingItemService.createSellingItem(sellingItem);
        if (createdSellingItem != null) {
            return new ResponseEntity<>(createdSellingItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<SellingItem>> getAllSellingItems() {
        Iterable<SellingItem> sellingItems = sellingItemService.findAllSellingItems();
        if (sellingItems.iterator().hasNext()) {
            return new ResponseEntity<>(sellingItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<SellingItem> getSellingItemById(@PathVariable UUID itemId) {
        Optional<SellingItem> sellingItemOptional = sellingItemService.findSellingItemById(itemId);
        if (sellingItemOptional.isPresent()) {
            return new ResponseEntity<>(sellingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<SellingItem> updateSellingItem(@PathVariable UUID itemId, @RequestBody SellingItem updatedSellingItem) {
        SellingItem sellingItem = sellingItemService.updateSellingItem(itemId, updatedSellingItem);
        if (sellingItem != null) {
            return new ResponseEntity<>(sellingItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{itemId}")
    public ResponseEntity<SellingItem> deleteSellingItem(@PathVariable UUID itemId) {
        SellingItem sellingItem = sellingItemService.deleteSellingItem(itemId);
        if (sellingItem != null) {
            return new ResponseEntity<>(sellingItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}