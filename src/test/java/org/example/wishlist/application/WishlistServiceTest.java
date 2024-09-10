package org.example.wishlist.application;

import org.example.wishlist.domain.entity.Category;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.usecase.AddProductToWishlistUseCase;
import org.example.wishlist.domain.usecase.CheckIfProductIsInWishlistUseCase;
import org.example.wishlist.domain.usecase.ListAllProductsFromWishlistUseCase;
import org.example.wishlist.domain.usecase.RemoveProductFromWishlistUseCase;
import org.example.wishlist.presentation.dto.request.WishlistRequestDto;
import org.example.wishlist.presentation.dto.response.WishlistResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @InjectMocks
    private WishlistService wishlistService;

    @Mock
    private AddProductToWishlistUseCase addProductToWishlistUseCase;

    @Mock
    private RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;

    @Mock
    private ListAllProductsFromWishlistUseCase listAllProductsFromWishlistUseCase;

    @Mock
    private CheckIfProductIsInWishlistUseCase checkIfProductIsInWishlistUseCase;

    @DisplayName("The method 'listAllProductsFromClientWishlist' should return a set of products with response message 'Wishlist products of client with id: 1 listed successfully' when client have products in wishlist")
    @Test
    void listAllProductsFromClientWishlistShouldReturnSetOfProductsWhenClientHaveProducts() {
        Category jardinagem = Category.create("Jardinagem");
        Product product1 = Product.create("produto1", "imagemUrlProduto1", BigDecimal.ONE, jardinagem);
        product1.setId("1");
        Product product2 = Product.create("produto2", "imagemUrlProduto2", BigDecimal.TWO, Category.create("Computadores"));
        product2.setId("2");
        Product product3 = Product.create("produto3", "imagemUrlProduto3", BigDecimal.TEN, jardinagem);
        product3.setId("3");

        Set<Product> expectedResult = Set.of(product1, product2, product3);

        when(listAllProductsFromWishlistUseCase.execute(anyString()))
                .thenReturn(expectedResult);

        WishlistResponseDto result = wishlistService.listAllProductsFromClientWishlist("1");

        assertTrue(expectedResult.containsAll(result.wishlist()));
        assertEquals("Wishlist products of client with id: 1 listed successfully", result.responseMessage());
    }

    @DisplayName("The method 'listAllProductsFromClientWishlist' should return empty set with responseMessage 'Wishlist products of client with id: 1 listed successfully' when client does not have products in wishlist")
    @Test
    void listAllProductsFromClientWishlistShouldReturnEmptySetWhenClientDoesNotHaveProducts() {
        when(listAllProductsFromWishlistUseCase.execute(anyString()))
                .thenReturn(Collections.emptySet());

        WishlistResponseDto result = wishlistService.listAllProductsFromClientWishlist("1");

        assertTrue(result.wishlist().isEmpty());
        assertEquals("Wishlist products of client with id: 1 listed successfully", result.responseMessage());
    }

    @DisplayName("The method 'checkIfProductIsInClientWishlist' should return set with the product and responseMessage 'Product with id: 2 is in client's wishlist' when client have the product in wishlist")
    @Test
    void checkIfProductIsInClientWishlistShouldReturnSetWithTheProductWhenClientHaveTheProductInWishlist() {
        Product productMock = mock(Product.class);

        when(checkIfProductIsInWishlistUseCase.execute(anyString(), anyString()))
                .thenReturn(Set.of(productMock));

        WishlistResponseDto result = wishlistService.checkIfProductIsInClientWishlist("1", "2");

        assertTrue(result.wishlist().contains(productMock));
        assertEquals("Product with id: 2 is in client's wishlist", result.responseMessage());
    }

    @DisplayName("The method 'checkIfProductIsInClientWishlist' should return empty set and responseMessage 'Product with id: 2 is not in client's wishlist' when client does not have the product in wishlist")
    @Test
    void checkIfProductIsInClientWishlistShouldReturnEmptySetWhenClientHaveTheProductInWishlist() {
        when(checkIfProductIsInWishlistUseCase.execute(anyString(), anyString()))
                .thenReturn(Collections.emptySet());

        WishlistResponseDto result = wishlistService.checkIfProductIsInClientWishlist("1", "2");

        assertTrue(result.wishlist().isEmpty());
        assertEquals("Product with id: 2 is not in client's wishlist", result.responseMessage());
    }

    @DisplayName("The method 'addProductToClientWishlist' should return responseMessage 'Product with id: 2 was successfully added to the client's wishlist' when product is correctly added to client's wishlist")
    @Test
    void addProductToClientWishlistShouldReturnCorrectResponseMessage() {
        WishlistResponseDto result = wishlistService.addProductToClientWishlist(new WishlistRequestDto("1", "2"));

        assertEquals("Product with id: 2 was successfully added to the client's wishlist", result.responseMessage());
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should return responseMessage 'Product with id: 2 was successfully remove from the client's wishlist' when product is correctly remove from client's wishlist")
    @Test
    void removeProductFromClientWishlistShouldReturnCorrectResponseMessage() {
        WishlistResponseDto result = wishlistService.removeProductFromClientWishlist(new WishlistRequestDto("1", "2"));

        assertEquals("Product with id: 2 was successfully removed from the client's wishlist", result.responseMessage());
    }
}