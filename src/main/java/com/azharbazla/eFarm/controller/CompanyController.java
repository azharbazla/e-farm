package com.azharbazla.eFarm.controller;

import com.azharbazla.eFarm.DTO.response.CommonResponse;
import com.azharbazla.eFarm.DTO.response.PagingResponse;
import com.azharbazla.eFarm.entity.Company;
import com.azharbazla.eFarm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Company request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Company")
                        .data(companyService.create(request))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<Company> companies = companyService.getAll(page - 1, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(companies.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Companies")
                        .data(companies.getContent())
                        .paging(pagingResponse)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(companyService.getById(id))
                        .message("Successfully Get Company")
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Company request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Update Company")
                        .data(companyService.update(request))
                        .build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id) {
        companyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Delete Company")
                        .build());
    }
}
