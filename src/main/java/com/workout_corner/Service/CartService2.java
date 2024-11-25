//package com.workout_corner.Service;
//
//import com.workout_corner.Entity.Cart;
//import com.workout_corner.Entity.CartItem;
//import com.workout_corner.Entity.Product;
//import com.workout_corner.Entity.User;
//import com.workout_corner.Repo.CartRepo;
//import com.workout_corner.Repo.ProductRepo;
//import com.workout_corner.Repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CartService {
//    @Autowired
//    CartRepo cartRepo;
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    ProductRepo productRepo;
//
//    public Cart createCart(Long userId, Long productId) {
//        Cart cart = new Cart();
//        cart.setUser(userRepo.findById(userId).
//                orElseThrow(() -> new IllegalArgumentException("User not found")));
//        CartItem item = new CartItem();
//        item.setCart(cart);
//        item.setProduct(productRepo.findById(productId).
//                orElseThrow(() -> new IllegalArgumentException("Product not found")));
//        item.setQuantity(1);
//        List<CartItem> items = new ArrayList<>();
//        items.add(item);
//        cart.setItems(items);
//        return cartRepo.save(cart);
//    }
//
//    public CartItem addProductToCart(Long userId, Long cartId, Long productId) {
//        CartItem item = new CartItem();
//        Cart cart = cartRepo.findById(cartId)
//              .orElse(createCart(userId, productId));
//        Product product = productRepo.findById(productId).
//                orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        List<CartItem> items = cart.getItems();
//       if (items.stream().anyMatch(i -> i.getProduct().equals(product))){
//           item = items.stream().filter(i -> i.getProduct().equals(product)).findAny()
//                   .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//           item.setQuantity(item.getQuantity()+1);
//       }
//       else {
//           item.setCart(cart);
//           item.setProduct(product);
//           item.setQuantity(1);
//           items.add(item);
//           cart.setItems(items);
//       }
//        return item;
//    }
//
//    public void removeProductFromCart(Long cartId, Long productId) {
//        Cart cart = cartRepo.findById(cartId)
//                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
//        Product product = productRepo.findById(productId).
//                orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        List<CartItem> items = cart.getItems();
//        CartItem item = items.stream().filter(i -> i.getProduct().equals(product)).findAny()
//                 .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//       if(item.getQuantity() > 1)  item.setQuantity(item.getQuantity()-1);
//       else items.remove(item);
//    }
//
//    public void removeAllProductsFromCart(Long cartId, Long productId) {
//        Cart cart = cartRepo.findById(cartId)
//                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
//        Product product = productRepo.findById(productId).
//                orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        List<CartItem> items = cart.getItems();
//        CartItem item = items.stream().filter(i -> i.getProduct().equals(product)).findAny()
//                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        items.remove(item);
//    }
//
//    public Product getCart() {
//        return null;
//    }
//
//    public List<Product> getAllCarts() {
//        return null;
//    }
//
//    public List<Product> getAllProductsFromCart(Long id) {
//        if (!cartRepo.existsById(id)) {
//            throw new IllegalArgumentException("Cart not found");
//        }
//        return cartRepo.findById(id).get().getItems().stream().map(CartItem::getProduct).toList();
//    }
//
//    public void deleteCart(Long id) {
//        if (!cartRepo.existsById(id)) {
//            throw new IllegalArgumentException("Cart not found");
//        }
//        cartRepo.deleteById(id);
//    }
//}
