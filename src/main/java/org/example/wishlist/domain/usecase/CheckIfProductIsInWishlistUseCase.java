package org.example.wishlist.domain.usecase;

import org.example.wishlist.domain.entity.Product;

import java.util.Set;

public interface CheckIfProductIsInWishlistUseCase {
    Set<Product> execute(String clientId, String productId);
}
