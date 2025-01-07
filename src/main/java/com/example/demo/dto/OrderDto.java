package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDto {
    private Long id;
    private Date date;
    private double total;
    private Long userId;
    private Long customerId;
}
