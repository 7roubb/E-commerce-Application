package com.osama.ecommerceapplication.carts;


import com.osama.ecommerceapplication.cartItems.CartItemRequestDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class CartRequestDTO {

    @NotNull(message = "{cart.items.notNull}")
    private List<@NotNull(message = "{cart.item.productId.notNull}") CartItemRequestDTO> cartItems;

    @NotNull(message = "{cart.total.notNull}")
    @PositiveOrZero(message = "{cart.total.positiveOrZero}")
    private Double cartTotal;

    @NotNull(message = "{cart.customerId.notNull}")
    private Long customerId;
}