package com.osama.ecommerceapplication.cartItems;

import com.osama.ecommerceapplication.products.Product;
import com.osama.ecommerceapplication.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductRepository productRepository;


    public CartItem toEntity(CartItemRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + requestDTO.getProductId()));

        CartItem cartItem = new CartItem();
        cartItem.setCartProduct(product);
        cartItem.setCartItemQuantity(requestDTO.getCartItemQuantity());
        return cartItem;
    }

    public CartItemResponseDTO toResponseDTO(CartItem cartItem) {
        CartItemResponseDTO responseDTO = new CartItemResponseDTO();
        responseDTO.setCartItemId(cartItem.getCartItemId());
        responseDTO.setCartProduct(cartItem.getCartProduct());
        responseDTO.setCartItemQuantity(cartItem.getCartItemQuantity());
        return responseDTO;
    }
}