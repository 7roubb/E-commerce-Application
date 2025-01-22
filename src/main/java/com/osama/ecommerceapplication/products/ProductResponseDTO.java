package com.osama.ecommerceapplication.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Integer productId;
    private String productName;
    private Double price;
    private String description;
    private String manufacturer;
    private Integer quantity;
    private CategoryEnum category;
    private ProductStatus status;
}