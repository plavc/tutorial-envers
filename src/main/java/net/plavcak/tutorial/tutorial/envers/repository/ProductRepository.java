package net.plavcak.tutorial.tutorial.envers.repository;

import net.plavcak.tutorial.tutorial.envers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
