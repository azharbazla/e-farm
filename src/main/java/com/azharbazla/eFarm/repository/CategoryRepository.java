package com.azharbazla.eFarm.repository;

import com.azharbazla.eFarm.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String category);

    @Query(value = "SELECT * FROM m_category WHERE name = :category", nativeQuery = true)
    Optional<Category> findByNameCategory(@Param("category") String category);
}