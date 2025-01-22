package com.osama.ecommerceapplication.carts;

import com.osama.ecommerceapplication.cartItems.CartItem;
import com.osama.ecommerceapplication.exceptions.CustomExceptions;
import com.osama.ecommerceapplication.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart createCart(User customer) {
        cartRepository.findByCustomer_Username(customer.getUsername())
                .ifPresent(cart -> {
                    throw new CustomExceptions.CartAlreadyExistsException("Cart already exists for user: " + customer.getUsername());
                });

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByUser_Username(String username) {
        return Optional.ofNullable(cartRepository.findByCustomer_Username(username)
                .orElseThrow(() ->
                        new CustomExceptions.CartNotFoundException("Cart not found for user: " + username)
                ));
    }

    public Cart addCartItem(Long cartId, CartItem newItem) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    // Check if the item already exists
                    Optional<CartItem> existingItem = cart.getCartItems().stream()
                            .filter(item -> item.getCartProduct().getProductId().equals(newItem.getCartProduct().getProductId()))
                            .findFirst();

                    if (existingItem.isPresent()) {
                        // Update quantity
                        CartItem item = existingItem.get();
                        item.setCartItemQuantity(item.getCartItemQuantity() + newItem.getCartItemQuantity());
                    } else {
                        cart.getCartItems().add(newItem);
                    }

                    recalculateCartTotal(cart);
                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new CustomExceptions.CartNotFoundException("Cart with ID " + cartId + " not found."));
    }

    public Cart removeCartItem(Long cartId, Integer productId) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.getCartItems().removeIf(item -> item.getCartProduct().getProductId().equals(productId));
                    recalculateCartTotal(cart);
                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new CustomExceptions.CartNotFoundException("Cart with ID " + cartId + " not found."));
    }

    public Cart updateCartItemQuantity(Long cartId, Integer productId, Integer newQuantity) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.getCartItems().stream()
                            .filter(item -> item.getCartProduct().getProductId().equals(productId))
                            .findFirst()
                            .ifPresent(item -> {
                                item.setCartItemQuantity(newQuantity);
                            });

                    recalculateCartTotal(cart);
                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new CustomExceptions.CartNotFoundException("Cart with ID " + cartId + " not found."));
    }

    private void recalculateCartTotal(Cart cart) {
        double total = cart.getCartItems().stream()
                .mapToDouble(item -> item.getCartProduct().getPrice() * item.getCartItemQuantity())
                .sum();
        cart.setCartTotal(total);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new CustomExceptions.CartNotFoundException("Cart with ID " + cartId + " not found."));

        cartRepository.deleteById(cartId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return Optional.of(cartRepository.findAll())
                .filter(carts -> !carts.isEmpty())
                .orElseThrow(() -> new CustomExceptions.CartNotFoundException("No carts available."));
    }
}
