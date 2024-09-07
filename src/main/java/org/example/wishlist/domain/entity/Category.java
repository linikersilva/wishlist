package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "categories")
public class Category {

    public static Category create(final Integer id, final String name) {
        return new Category(id, name);
    }

    @Getter
    private final Integer id;

    @Getter
    private final String name;

    private Category(final Integer id, final String name) {
        this.id = Objects.requireNonNull(id, "Category id cannot be null");

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        this.name = name;
    }
}
