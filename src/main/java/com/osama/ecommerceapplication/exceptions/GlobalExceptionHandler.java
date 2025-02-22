package com.osama.ecommerceapplication.exceptions;

import com.osama.ecommerceapplication.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(CustomExceptions.UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyExistsException(CustomExceptions.UserAlreadyExistsException ex) {
        String message = messageSource
                .getMessage(
                        "exception.user.already.exists",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.CONFLICT
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid user data")
                .content(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomExceptions.UserNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(CustomExceptions.UserNotFound ex) {
        String message = messageSource
                .getMessage(
                        "user.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(message,
                        HttpStatus.NOT_FOUND)
                );
    }

    @ExceptionHandler(CustomExceptions.EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExists(CustomExceptions.EmailAlreadyExistsException ex) {
        String message = messageSource
                .getMessage("exception.email.already.exists",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(
                        message,
                        HttpStatus.CONFLICT)
                );
    }

    @ExceptionHandler(CustomExceptions.TaskNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleTaskNotFound(CustomExceptions.TaskNotFound ex) {
        String message = messageSource
                .getMessage(
                        "task.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.
                        error(
                                message,
                                HttpStatus.NOT_FOUND)
                );

    }

    @ExceptionHandler(CustomExceptions.WrongPasswordOrEmail.class)
    public ResponseEntity<ApiResponse<Void>> handleWrongPasswordOrEmail(CustomExceptions.WrongPasswordOrEmail ex) {
        String message = messageSource.
                getMessage(
                        "bad.credential",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.UNAUTHORIZED)
                );

    }

    @ExceptionHandler(CustomExceptions.RateLimitExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleRateLimitExceeded(CustomExceptions.RateLimitExceededException ex) {
        String message = messageSource.
                getMessage(
                        "rate.limit.exceeded",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.TOO_MANY_REQUESTS
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.AddressNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleAddressNotFoundException(CustomExceptions.AddressNotFound ex) {
        String message = messageSource.getMessage(
                "address.not.found",
                new Object[]{},
                LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.BAD_REQUEST
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.CartNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCartNotFoundException(CustomExceptions.CartNotFoundException ex) {
        String message = messageSource
                .getMessage(
                        "exception.cart.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.CartAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleCartAlreadyExistsException(CustomExceptions.CartAlreadyExistsException ex) {
        String message = messageSource
                .getMessage(
                        "exception.cart.already.exists",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.CONFLICT
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.CartItemNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCartItemNotFoundException(CustomExceptions.CartItemNotFoundException ex) {
        String message = messageSource
                .getMessage(
                        "exception.cart.item.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.InvalidCartUpdateException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCartUpdateException(CustomExceptions.InvalidCartUpdateException ex) {
        String message = messageSource
                .getMessage(
                        "exception.cart.invalid.update",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.BAD_REQUEST
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.InvalidCartTotalException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCartTotalException(CustomExceptions.InvalidCartTotalException ex) {
        String message = messageSource
                .getMessage(
                        "exception.cart.invalid.total",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.BAD_REQUEST
                        )
                );
    }
    @ExceptionHandler(CustomExceptions.OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleOrderNotFoundException(CustomExceptions.OrderNotFoundException ex) {
        String message = messageSource
                .getMessage(
                        "exception.order.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.PaymentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handlePaymentNotFoundException(CustomExceptions.PaymentNotFoundException ex) {
        String message = messageSource
                .getMessage(
                        "exception.payment.not.found",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.InvalidOrderUpdateException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidOrderUpdateException(CustomExceptions.InvalidOrderUpdateException ex) {
        String message = messageSource
                .getMessage(
                        "exception.order.invalid.update",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.BAD_REQUEST
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.InvalidOrderCreationException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidOrderCreationException(CustomExceptions.InvalidOrderCreationException ex) {
        String message = messageSource
                .getMessage(
                        "exception.order.invalid.creation",
                        new Object[]{ex.getMessage()},
                        LocaleContextHolder.getLocale()
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .error(
                                message,
                                HttpStatus.BAD_REQUEST
                        )
                );
    }

    @ExceptionHandler(CustomExceptions.ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFoundException(CustomExceptions.ProductNotFoundException ex) {
        String message = messageSource.getMessage(
                "exception.product.not.found",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(message, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(CustomExceptions.ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductAlreadyExistsException(CustomExceptions.ProductAlreadyExistsException ex) {
        String message = messageSource.getMessage(
                "exception.product.already.exists",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT));
    }
}
