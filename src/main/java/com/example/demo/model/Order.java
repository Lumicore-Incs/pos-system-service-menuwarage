package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId")
    private Customer customerId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

}
