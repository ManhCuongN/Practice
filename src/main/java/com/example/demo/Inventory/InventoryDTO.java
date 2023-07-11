package com.example.demo.Inventory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InventoryDTO {
    @NotNull(message = "Quantity is not required")
    @Positive
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
