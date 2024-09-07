package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {

    public static Category create(final String id, final String name) {
        return new Category(id, name);
    }

    @Id
    @Getter
    private final String id;

    @Getter
    private final String name;

    private Category(final String id, final String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Category id cannot be null or empty");
        }
        this.id = id;

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        this.name = name;
    }
}
