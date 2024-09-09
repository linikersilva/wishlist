package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "clients")
public class Client {

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
            throw new IllegalArgumentException("Product is already in the wishlist");
        }

        this.wishlist.add(product);
    }

    public void removeProductFromClientWishlist(Product product) {
        if (this.wishlist.isEmpty()) {
            throw new IllegalArgumentException("Client's wishlist is empty");
        }

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (!this.wishlist.contains(product)) {
            throw new IllegalArgumentException("Product is not in the wishlist");
        }

        this.wishlist.removeIf(wishlistProduct -> wishlistProduct.getId().equals(product.getId()));
    }
}
