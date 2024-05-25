package id.ac.ui.cs.advprog.youkoso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "selling_items")
public class SellingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "quantity")
    private int quantity;
}
