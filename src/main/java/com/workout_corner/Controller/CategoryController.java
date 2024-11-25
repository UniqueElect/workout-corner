package com.workout_corner.Controller;

import com.workout_corner.DTO.CategoryDTO;
import com.workout_corner.Entity.Category;
import com.workout_corner.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    private Category createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }
    @GetMapping("/{id}")
    private Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/all")
    private List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @PostMapping("edit/{id}")
    private Category editCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.editCategory(id, categoryDTO);
    }
    @PostMapping("/delete/{id}")
    private void deleteCategory(@PathVariable Long id){
         categoryService.deleteCategory(id);
    }
}
