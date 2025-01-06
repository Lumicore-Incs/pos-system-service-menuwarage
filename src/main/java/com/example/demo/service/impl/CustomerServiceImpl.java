package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.DemarcationDto;
import com.example.demo.model.Customer;
import com.example.demo.model.Demarcation;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.service.CustomerService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapperConfig modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, ModelMapperConfig modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto, Long id) {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isPresent()) {
            customerDto.setId(id);
            Customer customer = dtoToEntity(customerDto);
            Customer save = customerRepo.save(customer);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public CustomerDto delete(Long id) {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isPresent()) {
            customerRepo.deleteById(id);
            return entityToDto(byId.get());
        }else {
            return null;
        }
    }

    @Override
    public CustomerDto save(CustomerDto dto) {
        Customer customer = dtoToEntity(dto);
        Customer save = customerRepo.save(customer);
        return entityToDto(save);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> all = customerRepo.findAll();
        List<CustomerDto> getAll = new ArrayList<>();
        for (Customer customer : all) {
            getAll.add(entityToDto(customer));
        }
        return getAll;
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> byId = customerRepo.findById(id);
        return byId.map(this::entityToDto).orElse(null);
    }

    private Customer dtoToEntity(CustomerDto dto) {
        return modelMapper.modelMapper().map(dto, Customer.class);
    }

    private CustomerDto entityToDto(Customer demarcation) {
        return (demarcation == null) ? null : modelMapper.modelMapper().map(demarcation, CustomerDto.class);
    }
}
