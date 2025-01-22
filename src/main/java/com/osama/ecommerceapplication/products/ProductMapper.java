package com.osama.ecommerceapplication.products;

import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductMapper {

    public static Product toProduct(ProductRequestDTO productRequest) {
        return Optional.ofNullable(productRequest).map(req -> {
            Product product = new Product();
            product.setProductName(req.getProductName());
            product.setPrice(req.getPrice());
            product.setDescription(req.getDescription());
            product.setManufacturer(req.getManufacturer());
            product.setQuantity(req.getQuantity());
            product.setCategory(req.getCategory());
            product.setStatus(req.getStatus());
            return product;
        }).orElse(null);
    }

    public static ProductResponseDTO toProductResponse(Product product) {
        return Optional.ofNullable(product).map(p -> {
            ProductResponseDTO productResponse = new ProductResponseDTO();
            productResponse.setProductId(p.getProductId());
            productResponse.setProductName(p.getProductName());
            productResponse.setPrice(p.getPrice());
            productResponse.setDescription(p.getDescription());
            productResponse.setManufacturer(p.getManufacturer());
            productResponse.setQuantity(p.getQuantity());
            productResponse.setCategory(p.getCategory());
            productResponse.setStatus(p.getStatus());
            return productResponse;
        }).orElse(null);
    }
}