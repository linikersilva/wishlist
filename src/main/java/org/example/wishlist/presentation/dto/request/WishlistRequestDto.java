package org.example.wishlist.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record WishlistRequestDto(@NotNull(message = "clientId attribute cannot be null") String clientId,
                                 @NotNull(message = "productId attribute cannot be null") String productId) {}
