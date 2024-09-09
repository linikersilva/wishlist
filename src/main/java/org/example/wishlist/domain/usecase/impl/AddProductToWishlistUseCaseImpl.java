package org.example.wishlist.domain.usecase.impl;

import org.example.wishlist.domain.entity.Client;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.domain.exception.ClientNotFoundException;
import org.example.wishlist.domain.exception.ProductNotFoundException;
import org.example.wishlist.domain.usecase.AddProductToWishlistUseCase;
import org.example.wishlist.infrastructure.persistence.ClientRepository;
import org.example.wishlist.infrastructure.persistence.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AddProductToWishlistUseCaseImpl implements AddProductToWishlistUseCase {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public AddProductToWishlistUseCaseImpl(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void execute(String clientId, String productId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        Product item = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        client.addProductToClientWishlist(item);
        clientRepository.save(client);
    }
}
