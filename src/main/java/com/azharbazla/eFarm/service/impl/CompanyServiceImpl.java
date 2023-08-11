package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Company;
import com.azharbazla.eFarm.repository.CompanyRepository;
import com.azharbazla.eFarm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        try {
            return companyRepository.save(company);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email or Telephone Already Used");
        }
    }

    @Override
    public Company getById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found"));
    }

    @Override
    public Page<Company> getAll(Integer page, Integer size) {
        return companyRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Company update(Company company) {
        Company currentCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found"));

        if (currentCompany != null) {
            return companyRepository.save(company);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        companyRepository.delete(getById(id));
    }
}
