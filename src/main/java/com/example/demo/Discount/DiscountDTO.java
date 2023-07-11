package com.example.demo.Discount;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class DiscountDTO {

    @NotBlank(message = "Name is required")
    @NotEmpty
    @Length(max=50)
    private String name;
    @NotBlank(message = "Description is required")
    @NotEmpty
    private String description;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }


    @NotNull(message = "Active is required")
    private boolean active;

    public DiscountDTO(String name, String description,  double discount_percent, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.discount_percent = discount_percent;
    }

    @NotNull(message = "Discount Percent is required")
    @Positive
    private double discount_percent;

}
