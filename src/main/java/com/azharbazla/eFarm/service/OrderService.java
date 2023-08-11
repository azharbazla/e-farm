package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.DTO.ProductRequest.OrderRequest;
import com.azharbazla.eFarm.entity.Order;
import org.springframework.data.domain.Page;


public interface OrderService {
    Order create(OrderRequest order);
    Page<Order> getAll(Integer page, Integer size);
    Order getById(String id);
    Page<Order> getAllByCompanyId(String id, Integer page, Integer size);
}
