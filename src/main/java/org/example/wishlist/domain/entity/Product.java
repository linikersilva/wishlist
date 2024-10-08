package org.example.wishlist.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "products")
public class Product {

    public static Product create(final String name, final String imageUrl, final BigDecimal price, final Category category) {
        return new Product(name, imageUrl, price, category);
    }

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    private final String name;

    @Getter
    private final String imageUrl;

    @Getter
    private final BigDecimal price;

    @Getter
    private final Category category;

    private Product(final String name, final String imageUrl, final BigDecimal price, final Category category) {
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

        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
