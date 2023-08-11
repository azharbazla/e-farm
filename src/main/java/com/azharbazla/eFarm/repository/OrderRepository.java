package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "SELECT * FROM t_order WHERE company_id = :companyId", nativeQuery = true)
    List<Order> findByCompanyId(String companyId);
}
