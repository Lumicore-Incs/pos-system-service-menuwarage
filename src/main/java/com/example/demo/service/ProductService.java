package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    ProductDto delete(Long id);

    List<ProductDto> getAll();

    Object search(Long id);
}
