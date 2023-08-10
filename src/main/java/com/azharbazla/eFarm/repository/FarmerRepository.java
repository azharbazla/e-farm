package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String> {
}
