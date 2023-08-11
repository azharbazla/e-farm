package com.azharbazla.eFarm.controller;

import com.azharbazla.eFarm.DTO.ProductRequest.ProductRequest;
import com.azharbazla.eFarm.DTO.response.CommonResponse;
import com.azharbazla.eFarm.DTO.response.PagingResponse;
import com.azharbazla.eFarm.entity.Product;
import com.azharbazla.eFarm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Product")
                        .data(productService.create(request))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<Product> products = productService.getAll(page - 1, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(products.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Products")
                        .data(products.getContent())
                        .paging(pagingResponse)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(productService.getById(id))
                        .message("Successfully Get Product")
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Update Product")
                        .data(productService.update(request))
                        .build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Delete Product")
                        .build());
    }
}
