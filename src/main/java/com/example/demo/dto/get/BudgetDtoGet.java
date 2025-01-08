package com.example.demo.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BudgetDtoGet {
    private Long id;
    private Date date;
    private double amount;
    private UserDtoGet userId;
}
