package id.ac.ui.cs.advprog.youkoso.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    private String id;
    private int quantity;
    private double price;
    private String product;

    public Request(String id, int quantity, double price, String product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}