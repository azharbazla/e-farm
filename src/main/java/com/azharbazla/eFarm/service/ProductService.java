package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.DTO.ProductRequest.ProductRequest;
import com.azharbazla.eFarm.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);
    Product getById(String id);
    Page<Product> getAll(Integer page, Integer size);
    Product update(ProductRequest product);
    void deleteById(String id);
}
