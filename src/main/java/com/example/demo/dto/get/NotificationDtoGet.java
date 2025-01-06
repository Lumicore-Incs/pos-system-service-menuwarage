package com.example.demo.dto.get;

import com.example.demo.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NotificationDtoGet {
    private Long id;
    private String massage;
    private Data date;
    private OrderDto orderId;
}
