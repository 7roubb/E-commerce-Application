package com.osama.ecommerceapplication.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(Long productId);

    Page<ProductResponseDTO> getAllProducts(Pageable pageable);

    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO);

    Boolean deleteProduct(Long productId);

    Page<ProductResponseDTO> getProductsByCategory(CategoryEnum category, Pageable pageable);

    Page<ProductResponseDTO> getProductsByCategoryAndStatus(CategoryEnum category, ProductStatus status, Pageable pageable);

    Page<ProductResponseDTO> getProductsByStatus(ProductStatus status, Pageable pageable);

}