package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class Product {

    public static Product create(final String id, final String name, final String imageUrl, final BigDecimal price) {
        return new Product(id, name, imageUrl, price);
    }

    @Id
    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final String imageUrl;

    @Getter
    private final BigDecimal price;

    private Product(final String id, final String name, final String imageUrl, final BigDecimal price) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product id cannot be null or empty");
        }

        this.id = id;

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        this.name = name;

        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Product image url cannot be null or empty");
        }

        this.imageUrl = imageUrl;

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }

        this.price = price;
    }

}
