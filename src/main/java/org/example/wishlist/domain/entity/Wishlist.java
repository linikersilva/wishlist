package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "wishlists")
public class Wishlist {

    public static Wishlist create(final String id, final List<Product> items) {
        return new Wishlist(id, items);
    }

    @Id
    @Getter
    private final String id;

    private final List<Product> items;

    private Wishlist(final String id, final List<Product> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Wishlist id cannot be null or empty");
        }

        this.id = id;

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty or null");
        }

        this.items = items;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

}
