package com.example.demo.Attrubute_Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttributeRepository extends JpaRepository<AttributeProductEntity, UUID> {
}
