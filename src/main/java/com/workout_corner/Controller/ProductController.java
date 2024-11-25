package com.workout_corner.Controller;

import com.workout_corner.DTO.ProductDTO;
import com.workout_corner.Entity.Product;
import com.workout_corner.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/create")
    private Product createProduct(@RequestBody ProductDTO productDTO){
       return productService.createProduct(productDTO);
    }
    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/all")
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/get/{categoryId}")
    private List<Product> getAllProductsByCategoryId(@PathVariable Long categoryId){
        return productService.getAllProductsByCategoryId(categoryId);
    }
    @PostMapping("edit/{id}")
    private Product editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
       return productService.editProduct(id, productDTO);
    }
    @PostMapping("/delete/{id}")
    private void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
