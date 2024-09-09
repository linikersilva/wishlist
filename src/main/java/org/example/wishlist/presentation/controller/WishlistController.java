package org.example.wishlist.presentation.controller;

import jakarta.validation.Valid;
import org.example.wishlist.application.WishlistService;
import org.example.wishlist.presentation.dto.request.WishlistRequestDto;
import org.example.wishlist.presentation.dto.response.WishlistResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/clientId/{clientId}")
    public ResponseEntity<WishlistResponseDto> listAllProductsFromClientWishlist(@PathVariable String clientId) {
        WishlistResponseDto response = wishlistService.listAllProductsFromClientWishlist(clientId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<WishlistResponseDto> addProductToClientWishlist(@RequestBody @Valid
                                                                          WishlistRequestDto wishlistRequestDTO) {
        WishlistResponseDto response = wishlistService.addProductToClientWishlist(wishlistRequestDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<WishlistResponseDto> removeProductFromClientWishlist(@RequestBody @Valid
                                                                               WishlistRequestDto wishlistRequestDTO) {
        WishlistResponseDto response = wishlistService.removeProductFromClientWishlist(wishlistRequestDTO);
        return ResponseEntity.ok().body(response);
    }
}
