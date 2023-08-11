package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Order;
import com.azharbazla.eFarm.repository.OrderRepository;
import com.azharbazla.eFarm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
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
