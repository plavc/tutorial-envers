package net.plavcak.tutorial.tutorial.envers.repository;

import net.plavcak.tutorial.tutorial.envers.model.Product;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRevisionRepository extends RevisionRepository<Product, Long, Long> {

}
