package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Order;
import com.azharbazla.eFarm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Override
    public Order create(Order Order) {
        return null;
    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public Page<Order> getAll(Integer page, Integer size) {
        return null;
    }
}
