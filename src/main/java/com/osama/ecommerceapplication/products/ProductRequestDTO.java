package com.osama.ecommerceapplication.products;

import com.osama.ecommerceapplication.common.OnCreate;
import com.osama.ecommerceapplication.common.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotNull(groups = {OnUpdate.class})
    private Long productId;

    @NotBlank(message = "{product.name.notBlank}",
            groups = {OnCreate.class})
    @Size(max = 100, message = "{product.name.size}",
            groups = {OnUpdate.class})
    private String productName;

    @NotNull(message = "{product.price.notNull}",
            groups = {OnCreate.class})
    @Positive(message = "{product.price.positive}",
            groups = {OnCreate.class})
    private Double price;

    @NotBlank(message = "{product.description.notBlank}",
            groups = {OnCreate.class})
    @Size(max = 500, message = "{product.description.size}",
            groups = {OnCreate.class})
    private String description;

    @NotBlank(message = "{product.manufacturer.notBlank}",
            groups = {OnCreate.class})
    @Size(max = 100, message = "{product.manufacturer.size}",
            groups = {OnCreate.class})
    private String manufacturer;

    @NotNull(message = "{product.quantity.notNull}",
            groups = {OnCreate.class})
    @Min(value = 0, message = "{product.quantity.min}",
            groups = {OnCreate.class})
    private Integer quantity;

    @NotNull(message = "{product.category.notNull}",
            groups = {OnCreate.class})
    private CategoryEnum category;

    @NotNull(message = "{product.status.notNull}",
            groups = {OnCreate.class})
    private ProductStatus status;

}