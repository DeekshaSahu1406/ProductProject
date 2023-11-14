package com.product.repository;

import com.product.pojos.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameContainingIgnoreCase(String name);
//    Product fetchById(Long id);

    Product findByDetails(String details);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.details) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> findByNameOrDetails(String query, Pageable pageable);
}
