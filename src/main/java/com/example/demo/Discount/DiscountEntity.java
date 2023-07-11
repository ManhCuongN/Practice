package com.example.demo.Discount;

import com.example.demo.Product.ProductEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "discount")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_discount;

    @OneToOne(mappedBy = "discount")
    private ProductEntity product;
    private String name;
    private String description;

    private double discount_percent;
    private boolean active;

    public UUID getId_discount() {
        return id_discount;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    private boolean is_deleted;

    public DiscountEntity(){}

    public void setId_discount(UUID id_discount) {
        this.id_discount = id_discount;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public DiscountEntity(String name, String description, double discount_percent, boolean active) {
        this.name = name;
        this.description = description;
        this.discount_percent = discount_percent;
        this.active = active;
    }
}
