package org.example.wishlist.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductTest {

    private final Category categoryMock = mock(Category.class);

    @DisplayName("The method 'create' should correctly create product when valid name, imageUrl, price and category parameters")
    @Test
    void createShouldCorrectlyCreateProductWhenValidParameters() {


        Product newProduct = Product.create("name", "imageUrl", BigDecimal.ONE, categoryMock);

        assertEquals("name", newProduct.getName());
        assertEquals("imageUrl", newProduct.getImageUrl());
        assertEquals(BigDecimal.ONE, newProduct.getPrice());
        assertEquals(categoryMock, newProduct.getCategory());
    }

    @DisplayName("The method 'create' should throw an 'IllegalArgumentException' with 'Product name cannot be null or empty' message when name parameter is null, blank (with tabulation or with space) or empty")
    @Test
    void createShouldThrowIllegalArgumentExceptionWhenNameIsNullOrBlankOrEmpty() {
        String expectedExceptionMessage = "Product name cannot be null or empty";

        assertThrows(IllegalArgumentException.class,
                () -> Product.create(null, "imageUrl", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("      ", "imageUrl", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("   ", "imageUrl", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("", "imageUrl", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);
    }

    @DisplayName("The method 'create' should throw an 'IllegalArgumentException' with 'Product image url cannot be null or empty' message when imageUrl parameter is null, blank (with tabulation or with space) or empty")
    @Test
    void createShouldThrowIllegalArgumentExceptionWhenImageUrlIsNullOrBlankOrEmpty() {
        String expectedExceptionMessage = "Product image url cannot be null or empty";

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", null, BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "      ", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "   ", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "", BigDecimal.ONE, categoryMock),
                expectedExceptionMessage);
    }

    @DisplayName("The method 'create' should throw an 'IllegalArgumentException' with 'Product price must be a positive value' message when price parameter is null, zero or negative")
    @Test
    void createShouldThrowIllegalArgumentExceptionWhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "imageUrl", null, categoryMock),
                "Product price must be a positive value");

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "imageUrl", BigDecimal.ZERO, categoryMock),
                "Product price must be a positive value");

        assertThrows(IllegalArgumentException.class,
                () -> Product.create("name", "imageUrl", BigDecimal.valueOf(-1), categoryMock),
                "Product price must be a positive value");
    }
}