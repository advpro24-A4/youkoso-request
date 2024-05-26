package id.ac.ui.cs.advprog.youkoso.model.builder;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import java.util.UUID;

public class SellingItemBuilder {
    private UUID sellingItemId;
    private int sellingItemQuantity;
    private Request sellingItemRequest;

    public SellingItemBuilder sellingItemId(UUID sellingItemId) {
        this.sellingItemId = sellingItemId;
        return this;
    }

    public SellingItemBuilder sellingItemQuantity(int sellingItemQuantity) {
        this.sellingItemQuantity = sellingItemQuantity;
        return this;
    }

    public SellingItemBuilder sellingItemRequest(Request sellingItemRequest) {
        this.sellingItemRequest = sellingItemRequest;
        return this;
    }

    public SellingItem build() {
        SellingItem sellingItem = new SellingItem();
        sellingItem.setId(this.sellingItemId);
        sellingItem.setQuantity(this.sellingItemQuantity);
        sellingItem.setRequest(this.sellingItemRequest);
        return sellingItem;
    }
}