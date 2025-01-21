package com.osama.ecommerceapplication.carts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osama.ecommerceapplication.products.Product;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartItemId;


    @OneToOne
    @JsonIgnoreProperties(value={
            "productId",
            "seller",
            "quantity"

    })
    private Product cartProduct;

    private Integer cartItemQuantity;

}