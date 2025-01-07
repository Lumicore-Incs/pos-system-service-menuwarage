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
public class NotificationDto {
    private Long id;
    private String massage;
    private Date date;
    private Long orderId;
}
