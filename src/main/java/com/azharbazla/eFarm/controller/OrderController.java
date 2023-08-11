package com.azharbazla.eFarm.controller;

import com.azharbazla.eFarm.DTO.ProductRequest.OrderRequest;
import com.azharbazla.eFarm.DTO.response.CommonResponse;
import com.azharbazla.eFarm.DTO.response.PagingResponse;
import com.azharbazla.eFarm.entity.Order;
import com.azharbazla.eFarm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Order")
                        .data(orderService.create(request))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<Order> orders = orderService.getAll(page - 1, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(orders.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Orders")
                        .data(orders.getContent())
                        .paging(pagingResponse)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(orderService.getById(id))
                        .message("Successfully Get Order")
                        .build());
    }

    @GetMapping(path = "/company/{id}")
    public ResponseEntity<?> getAllByCompanyId(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<Order> orders = orderService.getAllByCompanyId(id, page - 1, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(orders.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Orders")
                        .data(orders.getContent())
                        .paging(pagingResponse)
                        .build());
    }
}
