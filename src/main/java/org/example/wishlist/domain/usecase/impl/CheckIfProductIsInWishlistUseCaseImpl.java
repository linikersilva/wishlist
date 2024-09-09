package org.example.wishlist.domain.usecase.impl;

import org.example.wishlist.domain.entity.Client;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.exception.ClientNotFoundException;
import org.example.wishlist.domain.usecase.CheckIfProductIsInWishlistUseCase;
import org.example.wishlist.infrastructure.persistence.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CheckIfProductIsInWishlistUseCaseImpl implements CheckIfProductIsInWishlistUseCase {
    private final ClientRepository clientRepository;

    public CheckIfProductIsInWishlistUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Product> execute(String clientId, String productId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return client.getWishlist().stream()
                .filter(wishlistProduct -> wishlistProduct.getId().equals(productId))
                .collect(Collectors.toUnmodifiableSet());
    }
}
