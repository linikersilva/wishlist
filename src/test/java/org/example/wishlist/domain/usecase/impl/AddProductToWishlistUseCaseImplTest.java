package org.example.wishlist.domain.usecase.impl;

import org.example.wishlist.domain.entity.Client;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.exception.ClientNotFoundException;
import org.example.wishlist.domain.exception.ProductNotFoundException;
import org.example.wishlist.infrastructure.persistence.ClientRepository;
import org.example.wishlist.infrastructure.persistence.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddProductToWishlistUseCaseImplTest {

    @InjectMocks
    private AddProductToWishlistUseCaseImpl addProductToWishlistUseCaseImpl;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

    @DisplayName("The method 'execute' should correctly add a product to the client's wishlist when both clientId and productId are provided and exist.")
    @Test
    void executeShouldAddProductToWishlistWhenClientAndProductBothExists() {
        Client clientMock = mock(Client.class);
        Product productMock = mock(Product.class);

        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.of(clientMock));

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(productMock));

        when(clientMock.getWishlist()).thenReturn(Set.of(productMock));

        addProductToWishlistUseCaseImpl.execute("1", "1");

        assertTrue(clientMock.getWishlist().contains(productMock));
        verify(clientMock).addProductToClientWishlist(productMock);
        verify(clientRepository).save(clientMock);
    }

    @DisplayName("The method 'execute' should throw a 'ClientNotFoundException' with 'Client not found' message when the provided clientId does not exist.")
    @Test
    void executeShouldThrowClientNotFoundExceptionWhenClientDoesNotExist() {
        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        ClientNotFoundException clientNotFoundException =
                assertThrows(ClientNotFoundException.class, () -> addProductToWishlistUseCaseImpl.execute("not exists", "1"));

        assertEquals("Client not found", clientNotFoundException.getMessage());
    }

    @DisplayName("The method 'execute' should throw a 'ProductNotFoundException' with 'Product not found' message when the provided productId does not exist.")
    @Test
    void executeShouldThrowProductNotFoundExceptionWhenProductDoesNotExist() {
        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.of(mock(Client.class)));

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        ProductNotFoundException productNotFoundException =
                assertThrows(ProductNotFoundException.class, () -> addProductToWishlistUseCaseImpl.execute("1", "not exists"));

        assertEquals("Product not found", productNotFoundException.getMessage());
    }
}