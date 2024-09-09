package org.example.wishlist.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.example.wishlist.domain.entity.Product;

import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WishlistResponseDto(String responseMessage,
                                  Set<Product> wishlist) {}
