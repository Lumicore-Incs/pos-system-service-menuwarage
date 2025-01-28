package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "orderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productId")
    private Product productId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId")
    private Order orderId;
}
