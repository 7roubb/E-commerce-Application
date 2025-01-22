package com.osama.ecommerceapplication.products;

import com.osama.ecommerceapplication.exceptions.CustomExceptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.toProduct(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toProductResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException("Product ID: " + productId));
        return ProductMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toProductResponse);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
        return productRepository.findById(productRequestDTO.getProductId())
                .map(existingProduct -> {
                    Optional.ofNullable(productRequestDTO.getProductName()).ifPresent(existingProduct::setProductName);
                    Optional.ofNullable(productRequestDTO.getPrice()).ifPresent(existingProduct::setPrice);
                    Optional.ofNullable(productRequestDTO.getDescription()).ifPresent(existingProduct::setDescription);
                    Optional.ofNullable(productRequestDTO.getManufacturer()).ifPresent(existingProduct::setManufacturer);
                    Optional.ofNullable(productRequestDTO.getQuantity()).ifPresent(existingProduct::setQuantity);
                    Optional.ofNullable(productRequestDTO.getCategory()).ifPresent(existingProduct::setCategory);
                    Optional.ofNullable(productRequestDTO.getStatus()).ifPresent(existingProduct::setStatus);

                    Product updatedProduct = productRepository.save(existingProduct);
                    return ProductMapper.toProductResponse(updatedProduct);
                })
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException("Product ID: " + productRequestDTO.getProductId()));
    }

    @Override
    @Transactional
    public Boolean deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException("Product ID: " + productId));
        productRepository.delete(product);
        return true;
    }

    @Override
    @Transactional
    public Page<ProductResponseDTO> getProductsByCategory(CategoryEnum category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable)
                .map(ProductMapper::toProductResponse);
    }


    @Override
    @Transactional
    public Page<ProductResponseDTO> getProductsByCategoryAndStatus(CategoryEnum category, ProductStatus status, Pageable pageable) {
        return productRepository.findByCategoryAndStatus(category, status, pageable)
                .map(ProductMapper::toProductResponse);
    }


    @Override
    @Transactional
    public Page<ProductResponseDTO> getProductsByStatus(ProductStatus status, Pageable pageable) {
        return productRepository.findByStatus(status, pageable)
                .map(ProductMapper::toProductResponse);
    }

}
