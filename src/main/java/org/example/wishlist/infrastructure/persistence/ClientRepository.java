package org.example.wishlist.infrastructure.persistence;

import org.example.wishlist.domain.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, Integer> {
}
