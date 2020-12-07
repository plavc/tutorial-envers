package net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository;

import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
    JpaRepository<Product, Long>, RevisionRepository<Product, Long, Long> {

}
