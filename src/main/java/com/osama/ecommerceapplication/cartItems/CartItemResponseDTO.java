package com.osama.ecommerceapplication.cartItems;

import com.osama.ecommerceapplication.products.Product;
import lombok.Data;


@Data
public class CartItemResponseDTO {
    private Long cartItemId;

    private Product cartProduct;

    private Integer cartItemQuantity;

}
