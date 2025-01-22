package com.osama.ecommerceapplication.carts;
import com.osama.ecommerceapplication.cartItems.CartItem;
import com.osama.ecommerceapplication.users.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartResponseDTO {

    private Integer cartId;

    private List<CartItem> cartItems = new ArrayList<>();

    private Double cartTotal;

    private User customer;
}
