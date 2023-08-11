package com.azharbazla.eFarm.DTO.ProductRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private String category;
    private String farmerId;
    private Long price;
    private Integer stock;
}