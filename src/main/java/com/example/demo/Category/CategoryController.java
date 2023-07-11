package com.example.demo.Category;

import com.example.demo.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public List<CategoryEntity> getAllCategory() {
        List<CategoryEntity> category = categoryService.getAll();
        return category;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }

        CategoryEntity createdCategory = categoryService.createCategory(categoryDTO);
        if (createdCategory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create category", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK.value(), "Category created successfully", null));
    }

    @PatchMapping("/update/{idCategory}")
    public ResponseEntity<?> update(@PathVariable("idCategory")UUID idCategory, @Valid @RequestBody CategoryDTO categoryDTO,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = fieldErrors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
        }
        CategoryEntity existingCategory = categoryService.getCategoryById(idCategory);
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        CategoryEntity updatedCategory = categoryService.updateCategory(existingCategory);

        if (updatedCategory == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update category", null));
        }

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Category updated successfully", null));


    }

    @DeleteMapping("/delete/{idCategory}")
    public ResponseEntity<?> delete(@PathVariable("idCategory") UUID idCategory) {
        CategoryEntity category = categoryService.deleteCategory(idCategory);
        if (category != null) {
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Category deleted successfully", null));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/destroy/{idCategory}")
    public ResponseEntity<?> destroy(@PathVariable("idCategory") UUID idCategory) {
        categoryService.destroyCategory(idCategory);
        return  ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Category destroy successfully", null));
    }



}
