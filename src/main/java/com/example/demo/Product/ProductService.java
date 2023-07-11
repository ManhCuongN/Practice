package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import com.example.demo.Category.CategoryRepository;
import com.example.demo.Discount.DiscountEntity;
import com.example.demo.Discount.IDiscountRepository;
import com.example.demo.Inventory.InventoryDTO;
import com.example.demo.Inventory.InventoryEntity;
import com.example.demo.Inventory.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository, CategoryRepository categoryRepository, IDiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.categoryRepository = categoryRepository;
        this.discountRepository = discountRepository;
    }

    private  final CategoryRepository categoryRepository;
    private final IDiscountRepository discountRepository;

    public ProductEntity createProduct(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        // Lấy thông tin category, inventory và discount từ cơ sở dữ liệu hoặc các phương thức khác
        CategoryEntity category = categoryRepository.findById(productDTO.getId_category()).orElse(null);
        InventoryEntity inventory = inventoryRepository.findById(productDTO.getId_inventory()).orElse(null);
        DiscountEntity discount = discountRepository.findById(productDTO.getId_discount()).orElse(null);
        if (category == null || inventory == null || discount == null) {
            return null; // Xử lý lỗi khi không tìm thấy đối tượng liên quan
        }

        // Gán các thuộc tính từ ProductDTO sang ProductEntity
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setInventory(inventory);
        product.setPrice(productDTO.getPrice());
        product.setDiscount(discount);
        boolean discountExists = productRepository.existsByDiscountId(productDTO.getId_discount());
        if (discountExists) {
            return null; // Xử lý lỗi khi id_discount bị trùng
        }

        return productRepository.save(product);
    }

    public ProductEntity updateProduct(UUID idProduct, ProductDTO productDTO) {
        ProductEntity existingProduct = productRepository.findById(idProduct).orElse(null);
        CategoryEntity category = categoryRepository.findById(productDTO.getId_category()).orElse(null);
        InventoryEntity inventory = inventoryRepository.findById(productDTO.getId_inventory()).orElse(null);
        DiscountEntity discount = discountRepository.findById(productDTO.getId_discount()).orElse(null);
        if (existingProduct == null || category == null || inventory == null || discount == null) {
            return null; // Xử lý lỗi khi không tìm thấy đối tượng liên quan
        }

        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setInventory(inventory);
        existingProduct.setCategory(category);
        existingProduct.setDiscount(discount);
        existingProduct.setName(productDTO.getName());

        return productRepository.save(existingProduct);
    }

//    public Boolean findDiscount(UUID id) {
//        return productRepository.existsByDiscountId(id);
//    }



}
