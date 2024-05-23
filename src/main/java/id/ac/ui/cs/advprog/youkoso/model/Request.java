package id.ac.ui.cs.advprog.youkoso.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "product_request_name")
    private String product;

    private String currency;

    public double convertPriceToIDR() {
        double priceInIDR = 0.0;

        switch (currency) {
            case "USD":
                priceInIDR = price * 14000;
                break;
            case "JPY":
                priceInIDR = price * 130;
                break;
            case "IDR":
                priceInIDR = price;
                break;
        }

        return priceInIDR;
    }


}