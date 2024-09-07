package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Document(collection = "wishlists")
public class Wishlist {

    public static Wishlist create(final Integer id, final List<Product> items) {
        return new Wishlist(id, items);
    }

    @Getter
    private final Integer id;

    private final List<Product> items;

    private Wishlist(final Integer id, final List<Product> items) {
        this.id = Objects.requireNonNull(id);

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty or null");
        }
        this.items = items;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

}
