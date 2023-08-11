package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.DTO.ProductRequest.ProductRequest;
import com.azharbazla.eFarm.entity.Category;
import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.entity.Product;
import com.azharbazla.eFarm.repository.ProductRepository;
import com.azharbazla.eFarm.service.CategoryService;
import com.azharbazla.eFarm.service.FarmerService;
import com.azharbazla.eFarm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final FarmerService farmerService;

    @Override
    public Product create(ProductRequest request) {
        Category category = categoryService.getOrSave(request.getCategory());
        Farmer farmer = farmerService.getById(request.getFarmerId());

        return productRepository.saveAndFlush(Product.builder().name(request.getName())
                .description(request.getDescription())
                .category(category)
                .farmer(farmer)
                .price(request.getPrice())
                .stock(request.getStock())
                .isActive(true)
                .build());
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }

    @Override
    public Page<Product> getAll(Integer page, Integer size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Product update(ProductRequest request) {
        Product currentProduct = productRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        Category category = categoryService.getOrSave(request.getCategory());
        Farmer farmer = farmerService.getById(request.getFarmerId());
        return productRepository.saveAndFlush(currentProduct.toBuilder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .farmer(farmer)
                .price(request.getPrice())
                .stock(request.getStock())
                .isActive(true)
                .build());
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
