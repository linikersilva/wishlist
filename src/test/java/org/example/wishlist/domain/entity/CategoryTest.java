package org.example.wishlist.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @DisplayName("The method 'create' should correctly create category when valid name parameter")
    @Test
    void createShouldCorrectlyCreateProductWhenValidParameters() {
        Category newCategory = Category.create("name");

        assertEquals("name", newCategory.getName());
    }

    @DisplayName("The method 'create' should throw an 'IllegalArgumentException' with 'Category name cannot be null or empty' message when name parameter is null, blank (with tabulation or with space) or empty")
    @Test
    void createShouldThrowIllegalArgumentExceptionWhenNameIsNullOrBlankOrEmpty() {
        String expectedExceptionMessage = "Category name cannot be null or empty";

        assertThrows(IllegalArgumentException.class,
                () -> Category.create(null),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Category.create("      "),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Category.create("   "),
                expectedExceptionMessage);

        assertThrows(IllegalArgumentException.class,
                () -> Category.create(""),
                expectedExceptionMessage);
    }
}