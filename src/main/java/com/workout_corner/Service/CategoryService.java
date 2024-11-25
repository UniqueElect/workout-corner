package com.workout_corner.Service;

import com.workout_corner.DTO.CategoryDTO;
import com.workout_corner.Entity.Category;
import com.workout_corner.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepo.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category editCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
       return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
