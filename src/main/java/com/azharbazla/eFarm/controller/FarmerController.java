package com.azharbazla.eFarm.controller;

import com.azharbazla.eFarm.DTO.response.CommonResponse;
import com.azharbazla.eFarm.DTO.response.PagingResponse;
import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/farmer")
public class FarmerController {
    private final FarmerService farmerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Farmer request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Farmer")
                        .data(farmerService.create(request))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<Farmer> farmers = farmerService.getAll(page - 1, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(farmers.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Farmers")
                        .data(farmers.getContent())
                        .paging(pagingResponse)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(farmerService.getById(id))
                        .message("Successfully Get Farmer")
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Farmer request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Update Farmer")
                        .data(farmerService.update(request))
                        .build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id) {
        farmerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Delete Farmer")
                        .build());
    }
}
