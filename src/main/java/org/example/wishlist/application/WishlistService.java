package org.example.wishlist.application;

import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.usecase.AddProductToWishlistUseCase;
import org.example.wishlist.domain.usecase.CheckIfProductIsInWishlistUseCase;
import org.example.wishlist.domain.usecase.ListAllProductsFromWishlistUseCase;
import org.example.wishlist.domain.usecase.RemoveProductFromWishlistUseCase;
import org.example.wishlist.presentation.dto.request.WishlistRequestDto;
import org.example.wishlist.presentation.dto.response.WishlistResponseDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WishlistService {

    private final AddProductToWishlistUseCase addProductToWishlistUseCase;
    private final RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;
    private final ListAllProductsFromWishlistUseCase listAllProductsFromWishlistUseCase;
    private final CheckIfProductIsInWishlistUseCase checkIfProductIsInWishlistUseCase;

    public WishlistService(AddProductToWishlistUseCase addProductToWishlistUseCase,
                           RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase,
                           ListAllProductsFromWishlistUseCase listAllProductsFromWishlistUseCase,
                           CheckIfProductIsInWishlistUseCase checkIfProductIsInWishlistUseCase) {
        this.addProductToWishlistUseCase = addProductToWishlistUseCase;
        this.removeProductFromWishlistUseCase = removeProductFromWishlistUseCase;
        this.listAllProductsFromWishlistUseCase = listAllProductsFromWishlistUseCase;
        this.checkIfProductIsInWishlistUseCase = checkIfProductIsInWishlistUseCase;
    }

    public WishlistResponseDto listAllProductsFromClientWishlist(String clientId) {
        Set<Product> wishlistProducts = listAllProductsFromWishlistUseCase.execute(clientId);

        return WishlistResponseDto.builder()
                .responseMessage("Wishlist products of client with id: " + clientId + " listed successfully")
                .wishlist(wishlistProducts)
                .build();
    }

    public WishlistResponseDto checkIfProductIsInClientWishlist(String clientId, String productId) {
        Set<Product> wishlistProducts = checkIfProductIsInWishlistUseCase.execute(clientId, productId);

        String responseMessage = "Product with id: " + productId + " is in client's wishlist";
        if (wishlistProducts.isEmpty()) {
            responseMessage = "Product with id: " + productId + " is not in client's wishlist";
        }

        return WishlistResponseDto.builder()
                .responseMessage(responseMessage)
                .wishlist(wishlistProducts)
                .build();
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
                        + " was successfully removed from the client's wishlist")
                .build();
    }
}
