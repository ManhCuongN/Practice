package com.example.demo.Product;

import com.example.demo.Attrubute_Product.AttributeProductEntity;
import com.example.demo.Base.BaseEntity;
import com.example.demo.Category.CategoryEntity;
import com.example.demo.Discount.DiscountEntity;
import com.example.demo.Inventory.InventoryEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "products")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_product;

    private String name;

    @ManyToOne
    @JoinColumn(name="id_category")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "id_inventory")
    private InventoryEntity inventory;

    private float price;

    @OneToOne
    @JoinColumn(name = "id_discount")
    private DiscountEntity discount;

    public ProductEntity(UUID id_product, String name, CategoryEntity category, InventoryEntity inventory, float price, DiscountEntity discount, List<AttributeProductEntity> attribute) {
        this.id_product = id_product;
        this.name = name;
        this.category = category;
        this.inventory = inventory;
        this.price = price;
        this.discount = discount;
        this.attribute = attribute;
    }

    public UUID getId_product() {
        return id_product;
    }

    public void setId_product(UUID id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public ProductEntity() {
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DiscountEntity getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountEntity discount) {
        this.discount = discount;
    }

    public List<AttributeProductEntity> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<AttributeProductEntity> attribute) {
        this.attribute = attribute;
    }

    @OneToMany(mappedBy = "product")
    private List<AttributeProductEntity> attribute;
    public UUID getId_discount() {
        if (discount != null) {
            return discount.getId_discount();
        }
        return null;
    }
}
