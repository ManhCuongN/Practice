package com.example.demo.Attrubute_Product;

import com.example.demo.Category.CategoryDTO;
import com.example.demo.Category.CategoryEntity;
import com.example.demo.Discount.DiscountEntity;
import com.example.demo.Inventory.InventoryEntity;
import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;
    private final ProductRepository productRepository;

    public AttributeService(AttributeRepository attributeRepository, ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.productRepository = productRepository;
    }

    public AttributeProductEntity createAttribute(AttributeDTO attributeDTO) {
        AttributeProductEntity attributeProduct = new AttributeProductEntity();
        ProductEntity product = productRepository.findById(attributeDTO.getId_product()).orElse(null);
        if(product == null) {
            return null;
        }
        attributeProduct.setProduct(product);
        attributeProduct.setKey(attributeDTO.getKey());
        attributeProduct.setValue(attributeDTO.getValue());
        return attributeRepository.save(attributeProduct);
    }

    public AttributeProductEntity updateAttribute(UUID idAttribute, AttributeDTO attributeDTO) {
        ProductEntity existingProduct = productRepository.findById(attributeDTO.getId_product()).orElse(null);
        AttributeProductEntity attributeProduct  = attributeRepository.findById(idAttribute).orElse(null);

        if (existingProduct == null || attributeProduct == null) {
            return null; // Xử lý lỗi khi không tìm thấy đối tượng liên quan
        }

        attributeProduct.setKey(attributeDTO.getKey());
        attributeProduct.setValue(attributeDTO.getValue());
        attributeProduct.setProduct(existingProduct);

        return attributeRepository.save(attributeProduct);
    }
}
