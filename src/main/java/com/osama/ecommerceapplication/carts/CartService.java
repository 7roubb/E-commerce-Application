package com.osama.ecommerceapplication.carts;

import com.osama.ecommerceapplication.users.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart createCart(User customer);

    Optional<Cart> getCartByUser_Username(String username);

    Cart updateCart(Long cartId, List<CartItem> cartItems, Double cartTotal);

    void deleteCart(Long cartId);

    List<Cart> getAllCarts();
}
