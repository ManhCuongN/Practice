package com.example.demo.Discount;

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

@RequestMapping("/api/v1/discount")
@Controller
public class DiscountController {
    private final DiscountService discountService;
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DiscountDTO discountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }
        DiscountEntity createDiscount = discountService.createDiscount(discountDTO);
        if (createDiscount == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create discount", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Discount created successfully", null));

    }

    @PatchMapping("/update/{idDiscount}")
    public ResponseEntity<?> update(@PathVariable("idDiscount")UUID idDiscount, @Valid @RequestBody DiscountDTO discountDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }

        DiscountEntity updatedCategory = discountService.updateDiscount(idDiscount, discountDTO);

        if (updatedCategory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update category", null));
        }

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Category updated successfully", null));
    }

    @DeleteMapping("/delete/{idDiscount}")
    public ResponseEntity<?> delete(@PathVariable("idDiscount") UUID idDiscount) {
        DiscountEntity discount = discountService.deleteDiscount(idDiscount);
        if (discount != null) {
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Discount deleted successfully", null));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/destroy/{idDiscount}")
    public ResponseEntity<?> destroy(@PathVariable("idDiscount") UUID idDiscount) {
        discountService.destroyDiscount(idDiscount);
        return  ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Discount destroy successfully", null));
    }

}
