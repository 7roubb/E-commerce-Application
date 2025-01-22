package com.osama.ecommerceapplication.exceptions;

public class CustomExceptions {

    public static class UserNotFound extends RuntimeException {
        public UserNotFound(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String username) {
            super(username);
        }

    }

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String email) {
            super(email);
        }

    }

    public static class TaskNotFound extends RuntimeException {
        public TaskNotFound(String Title) {
            super(Title);
        }
    }

    public static class WrongPasswordOrEmail extends RuntimeException {
        public WrongPasswordOrEmail() {
            super();
        }
    }

    public static class RateLimitExceededException extends RuntimeException {
        public RateLimitExceededException(String message) {
            super(message);
        }
    }
    public static class AddressNotFound extends RuntimeException {
        public AddressNotFound() {
            super();
        }
    }
    public static class CartNotFoundException extends RuntimeException {
        public CartNotFoundException(String message) {
            super(message);
        }
    }

    public static class CartAlreadyExistsException extends RuntimeException {
        public CartAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class CartItemNotFoundException extends RuntimeException {
        public CartItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidCartUpdateException extends RuntimeException {
        public InvalidCartUpdateException(String message) {
            super(message);
        }
    }

    public static class InvalidCartTotalException extends RuntimeException {
        public InvalidCartTotalException(String message) {
            super(message);
        }
    }
    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class PaymentNotFoundException extends RuntimeException {
        public PaymentNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidOrderUpdateException extends RuntimeException {
        public InvalidOrderUpdateException(String message) {
            super(message);
        }
    }

    public static class InvalidOrderCreationException extends RuntimeException {
        public InvalidOrderCreationException(String message) {
            super(message);
        }
    }
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyExistsException extends RuntimeException {
        public ProductAlreadyExistsException(String productName) {
            super(productName);
        }
    }
}
