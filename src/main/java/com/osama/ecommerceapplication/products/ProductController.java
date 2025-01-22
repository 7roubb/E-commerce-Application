package com.osama.ecommerceapplication.products;


import com.osama.ecommerceapplication.common.ApiResponse;
import com.osama.ecommerceapplication.common.OnCreate;
import com.osama.ecommerceapplication.common.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponseDTO> getProductById(@PathVariable Long productId) {
        ProductResponseDTO productResponse = productService.getProductById(productId);
        return ApiResponse.success(productResponse, HttpStatus.OK, getMessage("product.get.success", productId.toString()));
    }

    @PostMapping
    public ApiResponse<ProductResponseDTO> createProduct(@Validated(OnCreate.class) @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponse = productService.createProduct(productRequestDTO);
        return ApiResponse.success(productResponse, HttpStatus.CREATED, getMessage("product.create.success", productRequestDTO.getProductName()));
    }

    @PutMapping
    public ApiResponse<ProductResponseDTO> updateProduct(@Validated(OnUpdate.class) @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponse = productService.updateProduct(productRequestDTO);
        return ApiResponse.success(productResponse, HttpStatus.OK, getMessage("product.update.success", productRequestDTO.getProductName()));
    }

    @DeleteMapping
    public ApiResponse<Boolean> deleteProduct(@RequestHeader Long productId) {
        Boolean flag = productService.deleteProduct(productId);
        return ApiResponse.success(flag, HttpStatus.OK, getMessage("product.delete.success", productId.toString()));
    }

    @GetMapping
    public ApiResponse<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponseDTO> products = productService.getAllProducts(pageable);
        return ApiResponse.success(products, HttpStatus.OK, getMessage("product.get.all.success", ""));
    }

    @GetMapping("/category")
    public ApiResponse<Page<ProductResponseDTO>> getProductsByCategory(
            @RequestHeader CategoryEnum category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponseDTO> products = productService.getProductsByCategory(category, pageable);
        return ApiResponse.success(products, HttpStatus.OK, getMessage("product.get.byCategory.success", category.name()));
    }

    @GetMapping("/category/status")
    public ApiResponse<Page<ProductResponseDTO>> getProductsByCategoryAndStatus(
            @RequestHeader CategoryEnum category,
            @RequestHeader ProductStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponseDTO> products = productService.getProductsByCategoryAndStatus(category, status, pageable);
        return ApiResponse.success(products, HttpStatus.OK, getMessage("product.get.byCategoryAndStatus.success", category.name() + ", " + status.name()));
    }

    @GetMapping("/status")
    public ApiResponse<Page<ProductResponseDTO>> getProductsByStatus(
            @RequestHeader ProductStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponseDTO> products = productService.getProductsByStatus(status, pageable);
        return ApiResponse.success(products, HttpStatus.OK, getMessage("product.get.byStatus.success", status.name()));
    }

    private String getMessage(String code, String message) {
        return messageSource.getMessage(code, new Object[]{message}, LocaleContextHolder.getLocale());
    }
}