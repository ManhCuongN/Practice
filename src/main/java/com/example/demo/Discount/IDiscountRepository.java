package com.example.demo.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDiscountRepository extends JpaRepository<DiscountEntity, UUID> {
}
