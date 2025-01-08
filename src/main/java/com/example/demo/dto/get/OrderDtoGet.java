package com.example.demo.dto.get;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDtoGet {
    private Long id;
    private Date date;
    private double total;
    private UserDto userId;
    private CustomerDto customerId;
}
