package com.osama.ecommerceapplication.carts;

import com.osama.ecommerceapplication.cartItems.CartItem;
import com.osama.ecommerceapplication.users.User;

import java.util.List;
import java.util.Optional;


public interface CartService {

    Cart createCart(User customer);


    Optional<Cart> getCartByUser_Username(String username);

    Cart addCartItem(Long cartId, CartItem newItem);


    Cart removeCartItem(Long cartId, Integer productId);

    Cart updateCartItemQuantity(Long cartId, Integer productId, Integer newQuantity);

    void deleteCart(Long cartId);

    List<Cart> getAllCarts();
}
