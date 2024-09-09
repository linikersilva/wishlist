package org.example.wishlist.domain.usecase;

import org.example.wishlist.domain.entity.Product;

import java.util.Set;

public interface ListAllProductsFromWishlistUseCase {
    Set<Product> execute(String clientId);
}
