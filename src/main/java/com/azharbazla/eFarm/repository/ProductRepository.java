package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.entity.Order;
import com.azharbazla.eFarm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM m_product WHERE product_id = :id", nativeQuery = true)
    Optional<Product> findByIdProduct(String id);
    @Query(value = "SELECT * FROM m_product", nativeQuery = true)
    Page<Product> findAllProduct(Pageable pageable);

}
