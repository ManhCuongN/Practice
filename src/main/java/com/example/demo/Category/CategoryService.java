package com.example.demo.Category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAll() {
        List<CategoryEntity> category = categoryRepository.findAllNotDeleted();
        if (category.isEmpty()) {
            return new ArrayList<>();
        }
        return category;
    }


    public CategoryEntity createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity updateCategory(CategoryEntity category) {

        return categoryRepository.save(category);
    }

    public CategoryEntity getCategoryById(UUID idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    public CategoryEntity deleteCategory(UUID idCategory) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(idCategory);
        if (categoryOptional.isPresent()) {
            CategoryEntity category = categoryOptional.get();
            category.setIs_deleted(true);
            return categoryRepository.save(category);
        }
        return null;
    }

    public void destroyCategory(UUID idCategory) {
        categoryRepository.deleteById(idCategory);
    }



}


