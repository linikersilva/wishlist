package org.example.wishlist.domain.entity;

import lombok.Getter;
import org.example.wishlist.domain.exception.AlreadyClientWishlistException;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Optional;

@Document(collection = "clients")
public class Client {

    public static Client create(final Integer id, final String name) {
        return new Client(id, name, null);
    }

    public static Client from(final Client client, final Wishlist wishlist) {
        return new Client(client.getId(), client.getName(), wishlist);
    }

    @Getter
    private final Integer id;

    @Getter
    private final String name;

    private final Wishlist wishlist;

    private Client(final Integer id, final String name, final Wishlist wishlist) {
        this.id = Objects.requireNonNull(id, "Client id cannot be null");

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }

        this.name = name;

        this.wishlist = wishlist;
    }

    public Client addWishlist(final Wishlist wishlist){
        if(alreadyHasWishlist()){
            throw new AlreadyClientWishlistException("Wishlist already exists in client");
        }
        return Client.from(this, wishlist);
    }

    public boolean alreadyHasWishlist() {
        return getWishlist().isPresent();
    }

    public Optional<Wishlist> getWishlist() {
        return Optional.ofNullable(wishlist);
    }
}
