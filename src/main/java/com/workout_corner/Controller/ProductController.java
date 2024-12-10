package com.workout_corner.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout_corner.DTO.ProductDTO;
import com.workout_corner.Entity.Product;
import com.workout_corner.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/create")
    public Product createProduct(
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image != null) {
            productDTO.setImage(image.getBytes());
        }
        return productService.createProduct(productDTO);
    }
//    @PostMapping("/create")              FOR POSTMAN TESTING
//    public Product createProduct(
//            @RequestPart("product") String productJson,
//            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
//        if (image != null) {
//            productDTO.setImage(image.getBytes());
//        }
//        return productService.createProduct(productDTO);
//    }
    @PostMapping("/edit/{id}")
    public Product editProduct(
            @PathVariable Long id,
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image != null) {
            productDTO.setImage(image.getBytes());
        }
        return productService.editProduct(id, productDTO);
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
    @PostMapping("/delete/{id}")
    private void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
