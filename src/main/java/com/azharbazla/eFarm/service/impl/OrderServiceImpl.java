package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.DTO.ProductRequest.ProductRequest;
import com.azharbazla.eFarm.entity.*;
import com.azharbazla.eFarm.repository.OrderRepository;
import com.azharbazla.eFarm.service.CompanyService;
import com.azharbazla.eFarm.service.OrderService;
import com.azharbazla.eFarm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CompanyService companyService;

    @Override
    public Order create(Order order) {
        Company company = companyService.getById(order.getCompany().getId());
        order.setTransDate(LocalDateTime.now());
        order.setCompany(company);

        Product product = productService.getById(order.getProduct().getId());
        ProductRequest productRequest = new ProductRequest();

        if (product.getStock() < order.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Stock");
        }
        product.setStock(product.getStock() - order.getQuantity());
        if (product.getStock() <= 0) {
            productService.update(productRequest.toBuilder()
                    .isActive(false)
                    .build());
        } else {
            productService.update(productRequest.toBuilder()
                    .stock(product.getStock())
                    .build());
        }

        return orderRepository.save(order);
    }

    @Override
    public Order getById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found"));
    }

    @Override
    public Page<Order> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findAll(pageable);

        List<Order> orderResponses = new ArrayList<>();
        for (Order order : orders.getContent()) {
            Order orderResponse = Order.builder()
                    .id(order.getId())
                    .transDate(order.getTransDate())
                    .company(order.getCompany())
                    .product(order.getProduct())
                    .quantity(order.getQuantity())
                    .build();
            orderResponses.add(orderResponse);
        }

        return new PageImpl<>(orderResponses, pageable, orders.getTotalElements());
    }
}
