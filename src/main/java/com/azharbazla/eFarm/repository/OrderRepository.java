package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "SELECT * FROM t_order WHERE company_id = :companyId", nativeQuery = true)
    List<Order> findByCompanyId(String companyId);

    @Query(value = "SELECT * FROM t_order WHERE order_id = :id", nativeQuery = true)
    Optional<Order> findByIdFarmer(String id);

    @Query(value = "SELECT * FROM t_order", nativeQuery = true)
    Page<Order> findAllfarmer(Pageable pageable);
}
