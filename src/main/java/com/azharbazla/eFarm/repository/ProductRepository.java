package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM m_product", nativeQuery = true)
    List<Product> findAllProduct();
}
