package com.example.demo.Attrubute_Product;

import com.example.demo.Category.CategoryDTO;
import com.example.demo.Category.CategoryEntity;
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
@RequestMapping("/api/v1/attribute")
@Controller
public class AttributeController {
    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody AttributeDTO attributeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }

        AttributeProductEntity createdAttribute = attributeService.createAttribute(attributeDTO);
        if (createdAttribute == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create attribute product", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Attribute Product created successfully", null));
    }

    @PatchMapping("/update/{idAttribute}")
    public ResponseEntity<?> update(@PathVariable("idAttribute") UUID idAttribute, @Valid @RequestBody AttributeDTO attributeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }
        AttributeProductEntity existingAttribute = attributeService.updateAttribute(idAttribute,attributeDTO);

        if (existingAttribute == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update Attribute", null));
        }

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Attribute updated successfully", null));


    }

}
