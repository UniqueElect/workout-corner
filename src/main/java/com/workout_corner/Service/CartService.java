package com.workout_corner.Service;

import com.workout_corner.Entity.Cart;
import com.workout_corner.Entity.CartItem;
import com.workout_corner.Entity.Product;
import com.workout_corner.Entity.User;
import com.workout_corner.Repo.CartRepo;
import com.workout_corner.Repo.ProductRepo;
import com.workout_corner.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    // Helper method to fetch cart or create a new one
    public Cart getOrCreateCart(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setItems(new ArrayList<>());
                    return cartRepo.save(newCart);
                });
    }

    public CartItem addProductToCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Check if the product is already in the cart
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cart.getItems().add(newItem);
            existingItem = newItem;
        }

        cartRepo.save(cart); // Save the updated cart
        return existingItem;
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not in cart"));

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
        } else {
            cart.getItems().remove(item);
        }

        cartRepo.save(cart);
    }

    public void removeAllQuantitiesOfProduct(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not in cart"));

        cart.getItems().remove(item);
        cartRepo.save(cart);
    }

    public List<Product> getAllProductsFromCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return cart.getItems().stream()
                .map(CartItem::getProduct)
                .toList();
    }

    public void deleteCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        cartRepo.delete(cart);
    }
}
