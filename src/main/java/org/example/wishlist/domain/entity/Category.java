package org.example.wishlist.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {

    public static Category create(final String name) {
        return new Category(name);
    }

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    private final String name;

    private Category(final String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        this.name = name;
    }
}
