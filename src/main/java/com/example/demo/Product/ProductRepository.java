package com.example.demo.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity, UUID> {
    @Query("SELECT COUNT(p) > 0 FROM ProductEntity p WHERE p.discount.id_discount = :discountId")
    boolean existsByDiscountId(@Param("discountId") UUID discountId);
}
