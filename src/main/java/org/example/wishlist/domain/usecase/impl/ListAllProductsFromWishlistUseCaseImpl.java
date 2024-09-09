package org.example.wishlist.domain.usecase.impl;

import org.example.wishlist.domain.entity.Client;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.exception.ClientNotFoundException;
import org.example.wishlist.domain.usecase.ListAllProductsFromWishlistUseCase;
import org.example.wishlist.infrastructure.persistence.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ListAllProductsFromWishlistUseCaseImpl implements ListAllProductsFromWishlistUseCase {
    private final ClientRepository clientRepository;

    public ListAllProductsFromWishlistUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Product> execute(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return client.getWishlist();
    }
}
