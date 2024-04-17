package id.ac.ui.cs.advprog.youkoso.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{}
