package org.example.wishlist.domain.exception;

public class ProductNotInWishlistException extends RuntimeException {
    public ProductNotInWishlistException(String message) {
        super(message);
    }
}
