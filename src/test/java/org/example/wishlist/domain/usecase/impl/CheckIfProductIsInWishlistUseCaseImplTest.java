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
class CheckIfProductIsInWishlistUseCaseImplTest {

    @InjectMocks
    private CheckIfProductIsInWishlistUseCaseImpl checkIfProductIsInWishlistUseCaseImpl;

    @Mock
    private ClientRepository clientRepository;

    @DisplayName("The method 'execute' should correctly check if a product is in the client's wishlist when both clientId and productId are provided and exist.")
    @Test
    void executeShouldCheckIfProductIsInWishlistWhenClientAndProductBothExists() {
        Client clientMock = mock(Client.class);
        Product productMock = mock(Product.class);

        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.of(clientMock));

        when(clientMock.getWishlist())
                .thenReturn(Set.of(productMock));

        when(productMock.getId())
                .thenReturn("1");

        Set<Product> wishlistProduct = checkIfProductIsInWishlistUseCaseImpl.execute("1", "1");

        assertTrue(wishlistProduct.contains(productMock));
        verify(clientMock).getWishlist();
    }

    @DisplayName("The method 'execute' should throw a 'ClientNotFoundException' with 'Client not found' message when the provided clientId does not exist.")
    @Test
    void executeShouldThrowClientNotFoundExceptionWhenClientDoesNotExist() {
        when(clientRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        ClientNotFoundException clientNotFoundException =
                assertThrows(ClientNotFoundException.class, () -> checkIfProductIsInWishlistUseCaseImpl.execute("not exists", "1"));

        assertEquals("Client not found", clientNotFoundException.getMessage());
    }
}