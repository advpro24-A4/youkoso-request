package id.ac.ui.cs.advprog.youkoso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Request {
    @Id
    private String id;
    private int quantity;
    private double price;
    private String product;


}