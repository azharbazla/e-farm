package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Company;
import com.azharbazla.eFarm.entity.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String> {
    @Query(value = "SELECT * FROM m_farmer WHERE farmer_id = :id", nativeQuery = true)
    Optional<Farmer> findByIdFarmer(String id);

    @Query(value = "SELECT * FROM m_farmer", nativeQuery = true)
    Page<Farmer> findAllfarmer(Pageable pageable);
}
