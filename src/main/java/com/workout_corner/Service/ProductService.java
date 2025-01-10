package com.workout_corner.Service;

import com.workout_corner.DTO.ProductDTO;
import com.workout_corner.Entity.Category;
import com.workout_corner.Entity.Product;
import com.workout_corner.Mapper.ProductMapper;
import com.workout_corner.Repo.CategoryRepo;
import com.workout_corner.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public Product createProduct(ProductDTO productDTO) {
        Category category = categoryRepo.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = ProductMapper.toEntity(productDTO, category);
        product.setCreatedAt(Instant.now());
        product.setImage(productDTO.getImage()); // Handle image data here

        return productRepo.save(product);
    }
    public Product editProduct(Long id, ProductDTO productDTO) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Category category = categoryRepo.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        if (productDTO.getImage() != null) {
            product.setImage(productDTO.getImage());
        }

        return productRepo.save(product);
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public List<Product> getAllProducts() {
       return productRepo.findAll();
    }
    public List<Product> filter(Long min, Long max, Long categoryId, String sortBy) {
        min = (min == null) ? 0L : min;
        max = (max == null) ? Long.MAX_VALUE : max;
        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            switch (sortBy) {
                case "priceAscending":
                    sort = Sort.by(Sort.Direction.ASC, "price");
                    break;
                case "priceDescending":
                    sort = Sort.by(Sort.Direction.DESC, "price");
                    break;
            }
        }
        return productRepo.findAllByPriceBetweenAndCategoryId(min, max, categoryId, sort);
    }
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
       return productRepo.findByCategoryId(categoryId);
    }

    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepo.deleteById(id);
    }
}
