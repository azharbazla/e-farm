package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Product;
import com.azharbazla.eFarm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public Product create(Product request) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public Page<Product> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
