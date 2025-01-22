package com.osama.ecommerceapplication.cartItems;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemRequestDTO {

    @NotNull(message = "{cart.item.productId.notNull}")
    private Long productId;

    @NotNull(message = "{cart.item.quantity.notNull}")
    @Min(value = 1, message = "{cart.item.quantity.min}")
    private Integer cartItemQuantity;
}