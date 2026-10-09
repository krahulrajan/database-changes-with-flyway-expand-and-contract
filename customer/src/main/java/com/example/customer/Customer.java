package com.example.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kept during expand phase to support backward-compatibility
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        setNames(firstName, lastName);
    }

    // DUAL-WRITE PATTERN: Writes to both new and old columns seamlessly
    public void setNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = (firstName + " " + (lastName != null ? lastName : "")).trim();
    }

    // DUAL-READ FALLBACK: Reads new column, falls back to legacy if null
    public String getFirstName() {
        if (firstName != null) return firstName;
        return fullName != null ? fullName.split(" ")[0] : null;
    }

    public String getLastName() {
        if (lastName != null) return lastName;
        if (fullName != null && fullName.contains(" ")) {
            return fullName.substring(fullName.indexOf(" ") + 1);
        }
        return "";
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
}