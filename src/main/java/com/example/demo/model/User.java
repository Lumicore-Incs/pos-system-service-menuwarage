package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;
    private String contact;
    private String role;
    private String nic;
    private String userName;
    private String password;

    public User(Long id, String name, String address, String email, String contact, String role, String nic, String userName, String password, Demarcation demarcationId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.role = role;
        this.nic = nic;
        this.userName = userName;
        this.password = password;
        this.demarcationId = demarcationId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "demarcationId")
    private Demarcation demarcationId;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Order> orders;
}
