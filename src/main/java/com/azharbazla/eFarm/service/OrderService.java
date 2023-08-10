package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    Order create(Order Order);
    Order getById(String id);
    Page<Order> getAll(Integer page, Integer size);
}
