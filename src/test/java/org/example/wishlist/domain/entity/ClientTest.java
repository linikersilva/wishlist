package org.example.wishlist.domain.entity;

import org.example.wishlist.domain.exception.EmptyWishlistException;
import org.example.wishlist.domain.exception.FullWishlistException;
import org.example.wishlist.domain.exception.ProductAlreadyInWishlistException;
import org.example.wishlist.domain.exception.ProductNotInWishlistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientTest {

    private Client client;
    private Product product;

    @BeforeEach
    void setup() {
        product = Product.create("a", "a", BigDecimal.ONE, null);
        product.setId("1");
        Set<Product> wishlist = new HashSet<>();
        wishlist.add(product);
        client = Client.create("name", wishlist);
    }

    @DisplayName("The method 'create' should correctly create client when valid name and wishlist parameters")
    @Test
    void createShouldCorrectlyCreateClientWhenValidParameters() {
        Set<Product> wishlist = Set.of(mock(Product.class));

        Client newClient = Client.create("name", wishlist);

        assertEquals("name", newClient.getName());
        assertEquals(wishlist, newClient.getWishlist());
    }

    @DisplayName("The method 'create' should create an empty set 'wishlist' when the wishlist parameter is null")
    @Test
    void createShouldCreateAnEmptySetWhenWishlistParameterIsNull() {
        Client newClient = Client.create("name", null);

        assertEquals("name", newClient.getName());
        assertEquals(Collections.emptySet(), newClient.getWishlist());
    }

    @DisplayName("The method 'create' should throw an 'IllegalArgumentException' with 'Client name cannot be null or empty' message when name parameter is null, blank (with tabulation or with space) or empty")
    @Test
    void createShouldThrowIllegalArgumentExceptionWhenNameIsNullOrBlankOrEmpty() {
        Set<Product> wishlist = Set.of(mock(Product.class));
        String expectedExceptionMessage = "Client name cannot be null or empty";

        assertThrows(IllegalArgumentException.class, () -> Client.create(null, wishlist), expectedExceptionMessage);
        assertThrows(IllegalArgumentException.class, () -> Client.create("      ", wishlist), expectedExceptionMessage);
        assertThrows(IllegalArgumentException.class, () -> Client.create("   ", wishlist), expectedExceptionMessage);
        assertThrows(IllegalArgumentException.class, () -> Client.create("", wishlist), expectedExceptionMessage);
    }

    @DisplayName("The method 'getWishlist' should throw an 'UnsupportedOperationException' with 'The returned set should be unmodifiable' message when trying to modify the returned set")
    @Test
    void getWishlistShouldReturnUnmodifiableSet() {
        Product productMock = mock(Product.class);

        Set<Product> unmodifiableWishlist = client.getWishlist();

        assertThrows(UnsupportedOperationException.class,
                () -> unmodifiableWishlist.add(productMock), "The returned set should be unmodifiable");

        assertThrows(UnsupportedOperationException.class,
                () -> unmodifiableWishlist.remove(productMock), "The returned set should be unmodifiable");

        assertThrows(UnsupportedOperationException.class,
                unmodifiableWishlist::clear, "The returned set should be unmodifiable");
    }

    @DisplayName("The method 'addProductToClientWishlist' should correctly add product to client's wishlist when valid product")
    @Test
    void addProductToClientWishlistShouldAddProductToClientWishlistWhenValidProduct() {
        Product productMock = mock(Product.class);

        client.addProductToClientWishlist(productMock);

        assertTrue(client.getWishlist().contains(productMock));
    }

    @DisplayName("The method 'addProductToClientWishlist' should throw an 'IllegalArgumentException' with 'Product cannot be null' message when product parameter is null")
    @Test
    void addProductToClientWishlistShouldThrowIllegalArgumentExceptionWhenProductIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> client.addProductToClientWishlist(null), "Product cannot be null");
    }

    @DisplayName("The method 'addProductToClientWishlist' should throw an 'IllegalArgumentException' with 'Product is already in the wishlist' message when product is already in the wishlist")
    @Test
    void addProductToClientWishlistShouldThrowIllegalArgumentExceptionWhenProductIsAlreadyInTheWishlist() {
        assertThrows(ProductAlreadyInWishlistException.class,
                () -> client.addProductToClientWishlist(product), "Product is already in the wishlist");
    }

    @DisplayName("The method 'addProductToClientWishlist' should throw a 'FullWishlistException' with 'The wishlist is full. The maximum number of products is 20' message when wishlist is full")
    @Test
    void addProductToClientWishlistShouldThrowFullWishlistExceptionWhenWishlistIsFull() {
        Set<Product> fullWishlist = IntStream.range(0, 20)
                .mapToObj(i -> {
                    Product productOfWishlist = product;
                    productOfWishlist.setId(String.valueOf(i));
                    return productOfWishlist;
                })
                .collect(Collectors.toSet());

        Client clientWithFullWishlist = Client.create("name", fullWishlist);

        assertThrows(FullWishlistException.class,
                () -> clientWithFullWishlist.addProductToClientWishlist(mock(Product.class)), "The wishlist is full. The maximum number of products is 20");
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should correctly remove product from the wishlist when informed product is in the wishlist")
    @Test
    void removeProductFromClientWishlistShouldCorrectlyRemoveProductWhenProductIsInTheWishlist() {
        client.removeProductFromClientWishlist(product);

        assertFalse(client.getWishlist().contains(product));
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should throw an 'EmptyWishlistException' with 'Client's wishlist is empty' message when client's wishlist is empty")
    @Test
    void removeProductFromClientWishlistShouldThrowEmptyWishlistExceptionWhenWishlistIsEmpty() {
        Client clientWithEmptyWishlist = Client.create("name", Collections.emptySet());

        assertThrows(EmptyWishlistException.class,
                () -> clientWithEmptyWishlist.removeProductFromClientWishlist(product), "Product is already in the wishlist");
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should throw an 'IllegalArgumentException' with 'Product cannot be null' message when informed product is null")
    @Test
    void removeProductFromClientWishlistShouldThrowIllegalArgumentExceptionWhenWishlistIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> client.removeProductFromClientWishlist(null), "Product cannot be null");
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should throw an 'ProductNotInWishlistException' with 'Product is not in the wishlist' message when informed product is not in the wishlist")
    @Test
    void removeProductFromClientWishlistShouldThrowProductNotInWishlistExceptionWhenProductIsNotInWishlist() {
        assertThrows(ProductNotInWishlistException.class,
                () -> client.removeProductFromClientWishlist(mock(Product.class)), "Product is not in the wishlist");
    }
}