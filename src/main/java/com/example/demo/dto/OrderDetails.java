package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetails {
    private Long orderDetailsId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String type;
    private Long productId;
    private Long orderId;
}
