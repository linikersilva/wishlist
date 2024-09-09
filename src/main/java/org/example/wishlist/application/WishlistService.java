package org.example.wishlist.application;

import org.example.wishlist.domain.usecase.AddProductToWishlistUseCase;
import org.example.wishlist.domain.usecase.RemoveProductFromWishlistUseCase;
import org.example.wishlist.presentation.dto.request.WishlistRequestDto;
import org.example.wishlist.presentation.dto.response.WishlistResponseDto;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final AddProductToWishlistUseCase addProductToWishlistUseCase;
    private final RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;

    public WishlistService(AddProductToWishlistUseCase addProductToWishlistUseCase,
                           RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase) {
        this.addProductToWishlistUseCase = addProductToWishlistUseCase;
        this.removeProductFromWishlistUseCase = removeProductFromWishlistUseCase;
    }

    public WishlistResponseDto addProductToClientWishlist(WishlistRequestDto wishlistRequestDTO) {
        addProductToWishlistUseCase.execute(wishlistRequestDTO.clientId(), wishlistRequestDTO.productId());
        return WishlistResponseDto.builder()
                .responseMessage("Product with id: " + wishlistRequestDTO.productId()
                               + " was successfully added to the client's wishlist")
                .build();
    }

    public WishlistResponseDto removeProductFromClientWishlist(WishlistRequestDto wishlistRequestDTO) {
        removeProductFromWishlistUseCase.execute(wishlistRequestDTO.clientId(), wishlistRequestDTO.productId());
        return WishlistResponseDto.builder()
                .responseMessage("Product with id: " + wishlistRequestDTO.productId()
                        + " was successfully remove from the client's wishlist")
                .build();
    }
}
