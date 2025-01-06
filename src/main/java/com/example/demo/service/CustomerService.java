package com.example.demo.service;

import com.example.demo.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto update(CustomerDto customerDto, Long id);

    CustomerDto delete(Long s);

    CustomerDto save(CustomerDto dto);

    List<CustomerDto> getAll();

    CustomerDto getById(Long s);
}
