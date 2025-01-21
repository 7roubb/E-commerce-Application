package com.osama.ecommerceapplication.carts;

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
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByUser_Username(String username) {
        return cartRepository.findByCustomer_Username(username);
    }

    @Override
    public Cart updateCart(Long cartId, List<CartItem> cartItems, Double cartTotal) {
        Optional<Cart> existingCartOpt = cartRepository.findById(cartId);
        if (existingCartOpt.isPresent()) {
            Cart existingCart = existingCartOpt.get();
            existingCart.setCartItems(cartItems);
            existingCart.setCartTotal(cartTotal);
            return cartRepository.save(existingCart);
        }
        return null;
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}