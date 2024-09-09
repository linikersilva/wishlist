package org.example.wishlist.domain.usecase.impl;

import org.example.wishlist.domain.entity.Client;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.exception.ClientNotFoundException;
import org.example.wishlist.infrastructure.persistence.ClientRepository;
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
class ListAllProductsFromWishlistUseCaseImplTest {

    @InjectMocks
    private ListAllProductsFromWishlistUseCaseImpl listAllProductsFromWishlistUseCaseImpl;

    @Mock
    private ClientRepository clientRepository;

    @DisplayName("The method 'execute' should correctly list all client's wishlist products when clientId is provided and exists.")
    @Test
    void executeShouldAddProductToWishlistWhenClientAndProductBothExists() {
        Client clientMock = mock(Client.class);

        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.of(clientMock));

        Set<Product> expectedResult = Set.of(mock(Product.class), mock(Product.class));
        when(clientMock.getWishlist()).thenReturn(expectedResult);

        Set<Product> wishlistProducts = listAllProductsFromWishlistUseCaseImpl.execute("1");

        assertTrue(wishlistProducts.containsAll(expectedResult));
        verify(clientMock).getWishlist();
    }

    @DisplayName("The method 'execute' should throw a 'ClientNotFoundException' with 'Client not found' message when the provided clientId does not exist.")
    @Test
    void executeShouldThrowClientNotFoundExceptionWhenClientDoesNotExist() {
        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        ClientNotFoundException clientNotFoundException =
                assertThrows(ClientNotFoundException.class, () -> listAllProductsFromWishlistUseCaseImpl.execute("not exists"));

        assertEquals("Client not found", clientNotFoundException.getMessage());
    }
}