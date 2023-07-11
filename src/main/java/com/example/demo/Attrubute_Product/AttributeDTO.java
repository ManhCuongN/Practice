package com.example.demo.Attrubute_Product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AttributeDTO {

    @NotEmpty(message = "Key is not empty")
    @NotNull
    private String key;
    @NotEmpty(message = "Value is not empty")
    @NotNull
    private String value;
    private UUID id_product;

    public UUID getId_product() {
        return id_product;
    }

    public void setId_product(UUID id_product) {
        this.id_product = id_product;
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


}
