package id.ac.ui.cs.advprog.youkoso.model.builder;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import java.util.UUID;

public class SellingItemBuilder {
    private int id;
    private int quantity;
    private Request request;

    public SellingItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public SellingItemBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public SellingItemBuilder setRequest(Request request) {
        this.request = request;
        return this;
    }

    public SellingItem build() {
        SellingItem sellingItem = new SellingItem();
        sellingItem.setId(this.id);
        sellingItem.setQuantity(this.quantity);
        sellingItem.setRequest(this.request);
        return sellingItem;
    }
}