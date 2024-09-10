package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.example.wishlist.domain.exception.EmptyWishlistException;
import org.example.wishlist.domain.exception.FullWishlistException;
import org.example.wishlist.domain.exception.ProductAlreadyInWishlistException;
import org.example.wishlist.domain.exception.ProductNotInWishlistException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "clients")
public class Client {

    private static final int WISHLIST_MAXIMUM_NUMBER_OF_PRODUCTS = 20;

    public static Client create(final String name, final Set<Product> wishlist) {
        return new Client(name, wishlist);
    }

    @Id
    @Getter
    private String id;

    @Getter
    private final String name;

    private final Set<Product> wishlist;

    private Client(final String name, Set<Product> wishlist) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }

        this.name = name;

        this.wishlist = Objects.requireNonNullElse(wishlist, new HashSet<>());
    }

    public Set<Product> getWishlist() {
        return Collections.unmodifiableSet(wishlist);
    }

    public void addProductToClientWishlist(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (this.wishlist.contains(product)) {
            throw new ProductAlreadyInWishlistException("Product is already in the wishlist");
        }

        if (this.wishlist.size() == WISHLIST_MAXIMUM_NUMBER_OF_PRODUCTS) {
            throw new FullWishlistException("The wishlist is full. The maximum number of products is " + WISHLIST_MAXIMUM_NUMBER_OF_PRODUCTS);
        }

        this.wishlist.add(product);
    }

    public void removeProductFromClientWishlist(Product product) {
        if (this.wishlist.isEmpty()) {
            throw new EmptyWishlistException("Client's wishlist is empty");
        }

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (!this.wishlist.contains(product)) {
            throw new ProductNotInWishlistException("Product is not in the wishlist");
        }

        this.wishlist.removeIf(wishlistProduct -> wishlistProduct.getId().equals(product.getId()));
    }
}
