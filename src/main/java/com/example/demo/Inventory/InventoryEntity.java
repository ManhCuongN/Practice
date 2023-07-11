package com.example.demo.Inventory;

import com.example.demo.Attrubute_Product.AttributeProductEntity;
import com.example.demo.Base.BaseEntity;
import com.example.demo.Product.ProductEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="inventory")
public class InventoryEntity extends BaseEntity {
 @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_inventory;

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    private boolean is_deleted;
    @OneToMany(mappedBy = "inventory")
    private List<ProductEntity> product;

    public InventoryEntity() {
    }

    public UUID getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(UUID id_inventory) {
        this.id_inventory = id_inventory;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InventoryEntity(UUID id_inventory, List<ProductEntity> product, Integer quantity) {
        this.id_inventory = id_inventory;
        this.product = product;
        this.quantity = quantity;
    }

    private Integer quantity;
}
