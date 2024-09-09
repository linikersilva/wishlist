package org.example.wishlist.domain.usecase;

public interface AddProductToWishlistUseCase {
    void execute(String clientId, String productId);
}
