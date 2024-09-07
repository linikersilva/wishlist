package org.example.wishlist.infrastructure.persistence;

import org.example.wishlist.domain.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, Integer> {
}
