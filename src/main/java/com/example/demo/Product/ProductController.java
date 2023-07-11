package com.example.demo.Product;

import com.example.demo.Inventory.InventoryDTO;
import com.example.demo.Inventory.InventoryEntity;
import com.example.demo.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/product")
@Controller
public class ProductController {
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final ProductService productService;

//    @GetMapping("/test/{idDiscount}")
//    public Boolean findDiscount(@PathVariable()UUID id) {
//        return productService.findDiscount(id);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }
        ProductEntity createProduct = productService.createProduct(productDTO);
        if (createProduct == null) {
            System.out.println("tạch");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create discount", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Discount created successfully", null));

    }

    @PatchMapping("/update/{idProduct}")
    public ResponseEntity<?> update(@PathVariable("idProduct") UUID idProduct, @Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }

        ProductEntity updateProduct = productService.updateProduct(idProduct, productDTO);

        if (updateProduct == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update inventory", null));
        }

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Inventory updated successfully", null));
    }
}
