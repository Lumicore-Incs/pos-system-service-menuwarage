package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "demarcation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demarcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String routeName;

    @OneToMany(mappedBy = "demarcationId", cascade = CascadeType.ALL)
    private List<User> users;

    public Demarcation(long id, String location) {
        this.id = id;
        this.routeName = location;
    }
}
