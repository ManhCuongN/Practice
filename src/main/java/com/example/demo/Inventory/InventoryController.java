package com.example.demo.Inventory;

import com.example.demo.Discount.DiscountDTO;
import com.example.demo.Discount.DiscountEntity;
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

@RequestMapping("/api/v1/inventory")
@Controller
public class InventoryController {
    private InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody InventoryDTO inventoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }
        InventoryEntity createInventory = inventoryService.createInventory(inventoryDTO);
        if (createInventory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create discount", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Discount created successfully", null));

    }

    @PatchMapping("/update/{idInventory}")
    public ResponseEntity<?> update(@PathVariable("idInventory") UUID idInventory, @Valid @RequestBody InventoryDTO inventoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }

        InventoryEntity updateInventory = inventoryService.updateInventory(idInventory, inventoryDTO);

        if (updateInventory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update inventory", null));
        }

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Inventory updated successfully", null));
    }



}
