package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapperConfig modelMapperConfig;
    private final ProductRepo productRepo;

    public ProductServiceImpl(ModelMapperConfig modelMapperConfig, ProductRepo productRepo) {
        this.modelMapperConfig = modelMapperConfig;
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product save = productRepo.save(dtoToEntity(productDto));
        return entityToDto(save);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            productDto.setId(id);
            Product save = productRepo.save(dtoToEntity(productDto));
            return entityToDto(save);
        }return null;
    }

    @Override
    public ProductDto delete(Long id) {
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            productRepo.deleteById(id);
            return entityToDto(byId.get());
        }return null;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> all = productRepo.findAll();
        List<ProductDto> list = new ArrayList<>();
        for (Product product : all) {
            list.add(entityToDto(product));
        }
        return list;
    }

    @Override
    public Object search(Long id) {
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            return entityToDto(byId.get());
        }return "[]";
    }

    private Product dtoToEntity(ProductDto dto) {
        return modelMapperConfig.modelMapper().map(dto, Product.class);
    }

    private ProductDto entityToDto(Product product) {
        return (product == null) ? null : modelMapperConfig.modelMapper().map(product, ProductDto.class);
    }
}
