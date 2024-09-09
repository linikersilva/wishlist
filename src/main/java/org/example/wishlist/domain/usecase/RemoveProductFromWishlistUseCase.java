package org.example.wishlist.domain.usecase;

public interface RemoveProductFromWishlistUseCase {
    void execute(String clientId, String productId);
}
