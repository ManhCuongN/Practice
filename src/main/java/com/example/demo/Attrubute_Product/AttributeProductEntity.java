package com.example.demo.Attrubute_Product;

import com.example.demo.Base.BaseEntity;
import com.example.demo.Product.ProductEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "attributeProduct")
public class AttributeProductEntity extends BaseEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_attribute;

    public AttributeProductEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    public AttributeProductEntity(UUID id_attribute, ProductEntity product, String key, String value) {
        this.id_attribute = id_attribute;
        this.product = product;
        this.key = key;
        this.value = value;
    }

    private String key;

    public UUID getId_attribute() {
        return id_attribute;
    }

    public void setId_attribute(UUID id_attribute) {
        this.id_attribute = id_attribute;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
