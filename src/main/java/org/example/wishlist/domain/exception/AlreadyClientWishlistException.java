package org.example.wishlist.domain.exception;

public class AlreadyClientWishlistException extends RuntimeException {
    public AlreadyClientWishlistException(String message) {
        super(message);
    }
}
