package id.ac.ui.cs.advprog.youkoso.model;

import jakarta.persistence.Column;

import java.util.Date;

public class Customer extends User {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "address", nullable = false)
    private String address;
}