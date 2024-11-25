package com.workout_corner.Controller;

import com.workout_corner.DTO.ProductDTO;
import com.workout_corner.Entity.Cart;
import com.workout_corner.Entity.CartItem;
import com.workout_corner.Entity.Product;
import com.workout_corner.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public Cart createCart(@RequestParam Long userId) {
        return cartService.getOrCreateCart(userId);
    }
    @PostMapping("/add")
    public CartItem addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        return cartService.addProductToCart(userId, productId);
    }

    @PostMapping("/remove")
    private void removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId){
        cartService.removeProductFromCart(userId, productId);
    }

    @PostMapping("/remove/all")
    private void removeAllQuantitiesOfProduct(@RequestParam Long userId, @RequestParam Long productId){  //remove all of one kind
        cartService.removeAllQuantitiesOfProduct(userId, productId);
    }

    @GetMapping("/get/{id}")
    private List<Product> getAllProductsFromCart(@PathVariable Long id){
        return cartService.getAllProductsFromCart(id);
    }

    @PostMapping("/delete/{id}")
    private void deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
    }
}
