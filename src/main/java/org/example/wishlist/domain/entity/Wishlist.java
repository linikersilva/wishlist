package org.example.wishlist.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "wishlists")
public class Wishlist {

    public static Wishlist create(final List<Product> items) {
        return new Wishlist(items);
    }

    private final List<Product> items;

    private Wishlist(final List<Product> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty or null");
        }
        this.items = items;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

}
