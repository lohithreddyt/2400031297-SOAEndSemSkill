package com.example.tourists.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tourists", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Tourist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String name;
    @Email @NotBlank @Column(nullable = false, unique = true) private String email;
    @NotBlank private String destination;
    @NotBlank private String country;
    private String visitDate;
    @Column(length = 2000) private String notes;

    public Tourist() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
