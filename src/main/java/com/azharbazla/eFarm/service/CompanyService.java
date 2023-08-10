package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.entity.Company;
import org.springframework.data.domain.Page;

public interface CompanyService {
    Company create(Company company);
    Company getById(String id);
    Page<Company> getAll(Integer page, Integer size);
    Company update(Company company);
    void deleteById(String id);
}
