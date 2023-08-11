package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query(value = "SELECT * FROM m_company WHERE company_id = :id", nativeQuery = true)
    Optional<Company> findByIdCompany(String id);

    @Query(value = "SELECT * FROM m_company", nativeQuery = true)
    Page<Company> findAllCompany(Pageable pageable);
}
