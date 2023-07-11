package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import com.example.demo.Discount.DiscountEntity;
import com.example.demo.Inventory.InventoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class ProductDTO {
    @NotBlank(message = "Name is required")
    @NotEmpty
    @Length(max=50)
    private String name;


    @NotNull
    private UUID id_category;

    @NotNull
    private UUID id_inventory;

    @NotNull
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId_category() {
        return id_category;
    }

    public void setId_category(UUID id_category) {
        this.id_category = id_category;
    }

    public UUID getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(UUID id_inventory) {
        this.id_inventory = id_inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UUID getId_discount() {
        return id_discount;
    }

    public void setId_discount(UUID id_discount) {
        this.id_discount = id_discount;
    }

    @NotNull
    private UUID id_discount;

}
